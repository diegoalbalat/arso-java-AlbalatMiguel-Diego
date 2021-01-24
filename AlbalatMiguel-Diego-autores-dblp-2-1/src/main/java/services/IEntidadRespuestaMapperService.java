package services;

import java.net.URI;

import modelo.Autores;
import modelo.atom.FeedType;
import modelo.hal.AutoresResource;

public interface IEntidadRespuestaMapperService {
	
	public FeedType autoresToAtom(URI uri, Autores autores, String busqueda);
	
	public AutoresResource autoresToHal(Autores autores, URI uri, String busqueda);

}
