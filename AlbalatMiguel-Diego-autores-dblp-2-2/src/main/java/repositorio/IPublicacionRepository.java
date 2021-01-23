package repositorio;

import java.util.List;

import modelo.Publicacion;
import modelo.PublicacionView;

public interface IPublicacionRepository {
	
	public Publicacion guardarPublicacion(String usuario, String autor, String urlAutor, String urlPubliacionDBPL);
	
	public List<PublicacionView> obtenerPublicacionesByUsuario(String usuario);
	
	public List<PublicacionView> obtenerPublicacionesByAutorUrl(String urlAutor);
}
