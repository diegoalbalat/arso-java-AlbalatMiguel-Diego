package services;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import commons.Utils;
import modelo.Autor;
import modelo.Autores;
import modelo.atom.DateTimeType;
import modelo.atom.EntryType;
import modelo.atom.FeedType;
import modelo.atom.IdType;
import modelo.atom.LinkType;
import modelo.atom.PersonType;
import modelo.atom.TextType;
import modelo.hal.AutorResource;
import modelo.hal.AutoresResource;
import modelo.hal.Embedded;
import modelo.hal.Links;
import modelo.hal.Self;

public class EntidadRespuestaMapperServiceImpl implements IEntidadRespuestaMapperService {

	public static EntidadRespuestaMapperServiceImpl unicaInstancia = null;

	public static EntidadRespuestaMapperServiceImpl getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new EntidadRespuestaMapperServiceImpl();
		}
		return unicaInstancia;
	}

	public EntidadRespuestaMapperServiceImpl() {

	}

	public FeedType autoresToAtom(URI uri, Autores autores, String busqueda) {
		FeedType nueva = new FeedType();
		IdType id = new IdType();
		id.setValue(uri.toString());
		//nueva.setId(uri.toString());
		LinkType link =  new LinkType();
		link.setHref(uri.toString());
		link.setRel("self");		
		TextType titulo= new TextType();
		titulo.setType("text");
		titulo.getContent().add("Resultado de búsqueda de "+busqueda);
		DateTimeType update = new DateTimeType();
		update.setValue(Utils.createFechaWithTime(Calendar.getInstance().getTime()));
		nueva.getIdOrLinkOrTitle().add(id);
		nueva.getIdOrLinkOrTitle().add(titulo);
		nueva.getIdOrLinkOrTitle().add(update);
	
		for (Autor autor : autores.getAutor()) {
			EntryType entrada = new EntryType();
			List<Object> atributes = entrada.getAuthorOrIdOrLink();
			PersonType author = new PersonType();
			author.getNameOrUri().add(autor.getNombre());
			atributes.add(author);
			IdType idTemp = new IdType();
			idTemp.setValue(autor.getUrl());
			atributes.add(idTemp);
			LinkType linkTemp = new LinkType();
			linkTemp.setHref(autor.getUrl());
			linkTemp.setRel("self");
			TextType tituloTemp = new TextType();
			tituloTemp.setType("text");
			tituloTemp.getContent().add(autor.getNombre());
			//atributes.add(autor.getUrl());
			atributes.add(linkTemp);
			atributes.add(tituloTemp);
			DateTimeType updateTemp = new DateTimeType();
			updateTemp.setValue(Utils.createFechaWithTime(Calendar.getInstance().getTime()));
			atributes.add(updateTemp);
			
			linkTemp = new LinkType();
			linkTemp.setHref(autor.getUrl());
			linkTemp.setRel("alternate");
			atributes.add(linkTemp);

			nueva.getIdOrLinkOrTitle().add(entrada);
		}
		
		return nueva;
	}

	@Override
	public AutoresResource autoresToHal(Autores autores, URI uri, String busqueda) {
		AutoresResource respuesta = new AutoresResource();
		List<Autor> autoresList = autores.getAutor();
		respuesta.set_links(new Links(new Self(uri.toString())));
		Embedded _embedded = new Embedded();
		for(Autor autor : autoresList) {
			AutorResource autorResource = new AutorResource(new Links(new Self(autor.getUrl())),autor.getUrl(), autor.getNombre(), autor.getId() );
			_embedded.getAutores().add(autorResource);
		}
		respuesta.setCount(autoresList.size());
		respuesta.setTotal(autoresList.size());
		respuesta.set_embedded(_embedded);
		return respuesta;
	}
}
