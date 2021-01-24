package service;

import java.net.URI;

import modelo.Autores;
import modelo.AutoresResource;
import modelo.FeedType;

public interface IEntidadRespuestaMapperService {
	
	public FeedType autoresToAtom(URI uri, Autores autores, String busqueda);
	
	public AutoresResource autoresToHal(Autores autores, URI uri, String busqueda);

}
