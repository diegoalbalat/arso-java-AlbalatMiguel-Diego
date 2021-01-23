package repositorio;

import java.util.List;

import modelo.Publicacion;

public interface IPublicacionRepository {
	
	public Publicacion guardarPublicacion(Publicacion nueva);
	
	public List<Publicacion> obtenerPublicacionesByUsuario(String usuario);
	
	public List<Publicacion> obtenerPublicacionesByAutorUrl(String urlAutor);
}
