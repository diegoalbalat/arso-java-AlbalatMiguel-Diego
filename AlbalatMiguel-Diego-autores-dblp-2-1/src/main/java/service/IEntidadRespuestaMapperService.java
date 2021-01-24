package service;

import java.net.URI;

import modelo.Autores;
import modelo.FeedType;

public interface IEntidadRespuestaMapperService {
	
	public FeedType autoresToAtom(URI uri, Autores autores, String busqueda);

}
