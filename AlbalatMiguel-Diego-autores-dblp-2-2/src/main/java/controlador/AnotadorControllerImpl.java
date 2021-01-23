package controlador;

import java.util.List;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import modelo.Publicacion;
import modelo.PublicacionView;
import repositorio.IPublicacionRepository;
import repositorio.PublicacionRepository;

public class AnotadorControllerImpl implements IAnotadorController {

	private IPublicacionRepository publicacionRepository;

	public AnotadorControllerImpl() {
		publicacionRepository = PublicacionRepository.getUnicaInstancia();
	}

	@Override
	@GraphQLMutation(name = "registrarPublicacion", description = "Registra la publicación pasada como argumento en la BBDD.")
	public Publicacion registrarPublicacion(@GraphQLArgument(name = "usuario") String usuario,
			@GraphQLArgument(name = "autor") String autor, @GraphQLArgument(name = "urlAutor") String urlAutor,
			@GraphQLArgument(name = "urlPublicacionDBPL") String urlPublicacionDBPL) {
		return publicacionRepository.guardarPublicacion(usuario, autor, urlAutor, urlPublicacionDBPL);

	}

	@Override
	@GraphQLQuery(name = "obtenerPublicacionesUsuario", description = "Devuelve las publicaciones realizadas por usuario con el correo pasado como argumento.")
	public List<PublicacionView> obtenerPublicacionesByUsuario(@GraphQLArgument(name = "usuario") String usuario) {
		return publicacionRepository.obtenerPublicacionesByUsuario(usuario);
	}

	@Override
	@GraphQLQuery(name = "obtenerPublicacionesAutor", description = "Devuelve las tareas pendientes del usuario con el correo pasado como argumento.")
	public List<PublicacionView> obtenerPublicacionesByAutor(@GraphQLArgument(name = "urlAutor") String urlAutor) {
		return publicacionRepository.obtenerPublicacionesByAutorUrl(urlAutor);
	}

}
