package controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

public class AutoresControllerImpl implements AutoresController {

	public final static String DBLP_URL = "https://dblp.org";
	public final static String DBLP_FIND_ENDPOINT = "/search/author/api?q=";

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
}