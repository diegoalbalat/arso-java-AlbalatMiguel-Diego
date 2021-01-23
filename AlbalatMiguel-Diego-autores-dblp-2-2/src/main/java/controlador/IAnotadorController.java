package controlador;

import java.util.List;

import modelo.Publicacion;
import modelo.PublicacionView;

public interface IAnotadorController {

	public Publicacion registrarPublicacion(String usuario, String autor, String urlAutor, String urlPublicacionDBPL);

	public List<PublicacionView> obtenerPublicacionesByUsuario(String usuario);

	public List<PublicacionView> obtenerPublicacionesByAutor(String urlAutor);
}
