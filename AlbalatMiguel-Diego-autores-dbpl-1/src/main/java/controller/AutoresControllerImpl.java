package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import commons.Utils;
import modelo.Autor;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;
import modelo.PaginasAsociadas;
import modelo.TipoAfiliacion;

public class AutoresControllerImpl implements AutoresController {

	public final static String DBLP_URL = "https://dblp.org";
	public final static String DBLP_FIND_ENDPOINT = "/search/author/api?q=";
	public final static String DBLP_RDF = ".rdf";
	public final static String GB_URL = "https://books.google.com/books/feeds/volumes?q=";

	@Override
	public Autores findAutores(String autor) {
		Autores autores = new Autores();
		// Realizar petición a DBLP
		String response = makeRequest(DBLP_URL + DBLP_FIND_ENDPOINT + autor);
		if (!response.isEmpty()) {
			obtenerArbolAutores(autores, response);
		} else {
			// No hay resultados, definir compartamiento
		}

		return autores;
	}

	@Override
	public InformacionAutor findInformacion(String urlAutor) {
		InformacionAutor infoAutor = new InformacionAutor();
		JAXBContext contexto;
		Pattern pattern = Pattern.compile("pid\\/(.*)");
		Matcher matcher = pattern.matcher(urlAutor);
		matcher.find();
		BigInteger pid = BigInteger.valueOf(Math.abs(matcher.group(1).hashCode()));
		File autorFile = new File("xml/" + pid + ".xml");
		if (!autorFile.exists()) {

			// Solicitar fichero rdf de DBLP
			String response = makeRequest(urlAutor + DBLP_RDF);
			System.out.println(response);
			getDblpInformation(infoAutor, response);
			// Solicitar ifnormación de Google Books
			getGBInformation(infoAutor, response);
			// SOlicitar ifnroamcion JSON
			getDBPediaInformation(infoAutor, response);
			// Guardar autor en XML
			try {
				contexto = JAXBContext.newInstance(InformacionAutor.class);
				Marshaller marshaller = contexto.createMarshaller();
				marshaller.setProperty("jaxb.formatted.output", true);
				marshaller.setProperty("jaxb.schemaLocation", "autor.xsd");
				marshaller.marshal(infoAutor, autorFile);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			try {
				contexto = JAXBContext.newInstance(InformacionAutor.class);
				Unmarshaller unmarshaller = contexto.createUnmarshaller();
				infoAutor = (InformacionAutor) unmarshaller.unmarshal(autorFile);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Devolver autor

		return infoAutor;
	}

	private void getDblpInformation(InformacionAutor infoAutor, String response) {
		try {
			Document doc = (Document) Utils.convertStringToXMLDocument(response);
			XPathFactory factoria = XPathFactory.newInstance();
			XPath xpath = factoria.newXPath();
			// xpath.setNamespaceContext(new EspacioNombresDBLP());
			XPathExpression consulta;
			NodeList resultado;
			Element element;
			consulta = xpath.compile("/RDF/Person/primaryFullCreatorName");

			// Extraer el nombre completo del autor
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			if (resultado.item(0) != null) {
				infoAutor.setNombreCompleto(resultado.item(0).getTextContent());
			}

			// Establecer afiliación primaria
			consulta = xpath.compile("/RDF/Person/primaryAffiliation");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			TipoAfiliacion tipoAfiliacion = new TipoAfiliacion();
			if (resultado.item(0) != null) {
				tipoAfiliacion.setAfiliacionPrimaria(resultado.item(0).getTextContent());
			}
			// Establecer afiliaciones secundarias
			consulta = xpath.compile("/RDF/Person/otherAffiliation");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < resultado.getLength(); i++) {
				element = (Element) resultado.item(i);
				tipoAfiliacion.getAfiliacionSecundaria().add(element.getTextContent());
			}

			// Establecer pagina de premios primaria
			consulta = xpath.compile("/RDF/Person/awardWebpage");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < resultado.getLength(); i++) {
				element = (Element) resultado.item(i);
				infoAutor.getPaginasPremios().add(element.getAttribute("rdf:resource"));
			}

			// Establecer pagina principal
			consulta = xpath.compile("/RDF/Person/primaryHomepage");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			PaginasAsociadas paginas = new PaginasAsociadas();
			if (resultado.item(0) != null) {
				paginas.setPaginaPrincipal(((Element) resultado.item(0)).getAttribute("rdf:resource"));
			}
			// Establecer paginas secundarias
			consulta = xpath.compile("/RDF/Person/webpage");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < resultado.getLength(); i++) {
				element = (Element) resultado.item(i);
				paginas.getPaginasSecundaria().add(element.getAttribute("rdf:resource"));
			}

			// Establecer libros de los cuales es el autor principal
			consulta = xpath.compile("/RDF/Person/authorOf");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < resultado.getLength(); i++) {
				element = (Element) resultado.item(i);
				infoAutor.getLibrosAutor().add(element.getAttribute("rdf:resource"));
			}

			// idAutor;
			consulta = xpath.compile("/RDF/Person/P2456");
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			if (resultado.item(0) != null) {
				infoAutor.setIdAutor(BigInteger.valueOf(Math.abs(resultado.item(0).getTextContent().hashCode())));
			}

			infoAutor.setAfiliacion(tipoAfiliacion);
			infoAutor.setPaginas(paginas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getGBInformation(InformacionAutor infoAutor, String response) {
	}
	private void getDBPediaInformation(InformacionAutor infoAutor, String response) {
	}
	private void obtenerArbolAutores(Autores autores, String response) {
		Document doc = Utils.convertStringToXMLDocument(response);
		XPathFactory factoria = XPathFactory.newInstance();
		XPath xpath = factoria.newXPath();
		XPathExpression consulta;
		NodeList resultado;
		Element element;

		// Generar arbol de objectos JAXB procesando la respuesta
		try {
			consulta = xpath.compile("//hit");
			// Obtenemos todos los resultados obtenidos
			resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
			System.out.println(resultado.toString());
			Autor autorTemp = new Autor();
			for (int i = 0; i < resultado.getLength(); i++) {
				autorTemp = new Autor();
				// Seleccionamos un resultado
				element = (Element) resultado.item(i);

				// Recogemos el id del resultado
				autorTemp.setId(new BigInteger(element.getAttribute("id")));

				// Recogemos el nombre del autor
				element = (Element) element.getElementsByTagName("info").item(0);
				autorTemp.setNombre(element.getElementsByTagName("author").item(0).getTextContent());

				// Recogemos la url del autor
				autorTemp.setUrl(element.getElementsByTagName("url").item(0).getTextContent());

				// Añadimos el resultado a la lista de autores
				autores.getAutor().add(autorTemp);

			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private String makeRequest(String url) {
		URL endpoint;
		String body = "";
		try {
			endpoint = new URL(url);
			URLConnection con = endpoint.openConnection();
			InputStream in = con.getInputStream();
			body = IOUtils.toString(in, "UTF-8");
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;

	}

	@Override
	public String crearFavoritos() {
		// Se genera un identificador para el documento
		String id = Utils.createId();
		// Se crea el fichero si no existe ya
		File docFavoritos = new File("xml/favoritos" + id + ".xml");
		if (!docFavoritos.exists()) {
			try {
				Favoritos favoritos = new Favoritos();
				favoritos.getUrlAutor();
				JAXBContext contexto = JAXBContext.newInstance(Favoritos.class);
				Marshaller marshaller = contexto.createMarshaller();
				marshaller.setProperty("jaxb.formatted.output", true);
				marshaller.setProperty("jaxb.schemaLocation","docFavoritos.xsd");
				marshaller.marshal(favoritos, docFavoritos);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
		}
		// Si existe se busca otro identificador
		else {
			return this.crearFavoritos();
		}

	}

	@Override
	public Favoritos findFavoritos(String identificador) {
		// Obtenemos el fichero y comprobamos que exista
		File docFavoritos = new File("xml/favoritos" + identificador + ".xml");
		if (docFavoritos.exists()) {
			// Realizamos el unmarshalling del fichero en la variable favoritos y lo
			// devolvemos
			JAXBContext contexto;
			Favoritos favoritos = new Favoritos();
			try {
				contexto = JAXBContext.newInstance(Favoritos.class);
				Unmarshaller unmarshaller = contexto.createUnmarshaller();
				favoritos = (Favoritos) unmarshaller.unmarshal(docFavoritos);

			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return favoritos;
		}
		// Si no existe el fichero devolvemos nulo (El error se comprobara en los
		// servicios)
		return null;
	}

	@Override
	public boolean deleteAutorFavoritos(String identificador, String urlAutor) {
		File docFavoritos = new File("xml/favoritos" + identificador + ".xml");
		if (docFavoritos.exists()) {
			// Realizamos el unmarshalling del fichero en la variable favoritos y lo devolvemos
			JAXBContext contexto;
			Favoritos favoritos = new Favoritos();
			try {
				contexto = JAXBContext.newInstance(Favoritos.class);
				Unmarshaller unmarshaller = contexto.createUnmarshaller();
				favoritos = (Favoritos) unmarshaller.unmarshal(docFavoritos);
				boolean removed = false;
				// Creamos un iterador para recorrer la lista y borrar la url en caso de encontrarla
				ListIterator<String> iter = favoritos.getUrlAutor().listIterator();
				while (iter.hasNext()) {
					if (iter.next().equals(urlAutor)) {
						iter.remove();
						removed = true;
					}
				}
				if(removed) {
					Marshaller marshaller = contexto.createMarshaller();
					marshaller.setProperty("jaxb.formatted.output", true);
					marshaller.setProperty("jaxb.schemaLocation","docFavoritos.xsd");
					marshaller.marshal(favoritos, docFavoritos);
					return true;
				}
				return false;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public Favoritos addAutorFavoritos(String identificador, String urlAutor) {
		File docFavoritos = new File("xml/favoritos" + identificador + ".xml");
		if (docFavoritos.exists()) {
			// Realizamos el unmarshalling del fichero en la variable favoritos y lo devolvemos
			JAXBContext contexto;
			Favoritos favoritos = new Favoritos();
			try {
				contexto = JAXBContext.newInstance(Favoritos.class);
				Unmarshaller unmarshaller = contexto.createUnmarshaller();
				favoritos = (Favoritos) unmarshaller.unmarshal(docFavoritos);
				if (favoritos.getUrlAutor().stream().filter(p -> p.equals(urlAutor)).collect(Collectors.toList()).size() == 0) {
					// Añadir
					favoritos.getUrlAutor().add(urlAutor);
					Marshaller marshaller = contexto.createMarshaller();
					marshaller.setProperty("jaxb.formatted.output", true);
					marshaller.setProperty("jaxb.schemaLocation","docFavoritos.xsd");
					marshaller.marshal(favoritos, docFavoritos);
				}
				else {
					// Ya existe
				}
				return favoritos;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean deleteBBDD() {
		// Se obtiene el directorio
		File folder = new File("/xml");
		try {
			// Se eliminan los ficheros del directorio que acaben en .xml
			Arrays.stream(folder.listFiles((f, p) -> p.endsWith(".xml"))).forEach(File::delete);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
