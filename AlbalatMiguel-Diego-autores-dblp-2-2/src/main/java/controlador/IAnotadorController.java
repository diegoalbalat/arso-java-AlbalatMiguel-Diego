package controlador;

import java.util.List;

import modelo.Publicacion;

public interface IAnotadorController {

	public void registrarPublicacion(Publicacion publicacion);

	public List<Publicacion> obtenerPublicacionesByUsuario(String usuario);

	public List<Publicacion> obtenerPublicacionesByAutor(String urlAutor);
}
