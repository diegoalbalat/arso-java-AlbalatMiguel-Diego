package controlador;

import java.util.List;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import modelo.Publicacion;
import repositorio.IPublicacionRepository;
import repositorio.PublicacionRepository;

public class AnotadorControllerImpl implements IAnotadorController {
	
    private IPublicacionRepository publicacionRepository;

    public AnotadorControllerImpl() {
    	publicacionRepository = PublicacionRepository.getUnicaInstancia();
    }
	@Override
	@GraphQLQuery(name = "registrarPublicación",
    description = "Registra la publicación pasada como argumento en la BBDD.")
	public void registrarPublicacion(@GraphQLArgument(name = "publicacion") Publicacion publicacion) {
		publicacionRepository.guardarPublicacion(publicacion);
		
	}

	@Override
	@GraphQLQuery(name = "obtenerPubliacionesUsuario",
    description = "Devuelve las publicaciones realizadas por usuario con el correo pasado como argumento.")
	public List<Publicacion> obtenerPublicacionesByUsuario(String usuario) {
		return publicacionRepository.obtenerPublicacionesByUsuario(usuario);
	}

	@Override
	@GraphQLQuery(name = "obtenerPublicacionesAutor",
    description = "Devuelve las tareas pendientes del usuario con el correo pasado como argumento.")
	public List<Publicacion> obtenerPublicacionesByAutor(String urlAutor) {
		return publicacionRepository.obtenerPublicacionesByAutorUrl(urlAutor);
	}

}
