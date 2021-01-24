package controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import modelo.Autores;
import modelo.InformacionAutor;

@Path("autores")
@Produces(MediaType.APPLICATION_JSON)
@Api(produces = "application/json", value = "Operations pertaining to manager blood donors in the application")
public class AutoresRestController {

	@Context
	private UriInfo uriInfo;

	private IAutoresController autorController = AutoresControllerImpl.getUnicaInstancia();

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Devuelve un listado de tipo autor encontrado al filtrar por el término de búsqueda indicado.", response = Autores.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Devuelve el listado asociado al término de búsqueda"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "La busqueda no ha obtenido ningún resultado"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response findAutores(
			@ApiParam(name = "autor", value = "Término de búsqueda de autores", required = true) @NotNull @QueryParam("autor") String autor) {
		if (StringUtils.isEmpty(autor)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			Autores autores = autorController.findAutores(autor);
			if (autores.getAutor().size() == 0) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			// Comprobar tipo cabecera
			// Devolver atom xml
			// Devolver hal json
			return Response.ok().entity(autores).build();
		} catch (AutorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("/{urlAutor}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Operación que recupera de DBPL, GB y DBPedia la información del autor pasado como párametro siendo representado por su url en DBPL.", response = InformacionAutor.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Devuelve la información recopilada del autor."),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "La busqueda no ha obtenido ninguna información."),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response findInformacionAutor(
			@ApiParam(name = "urlAutor", value = "Url de identificación del autor", required = true) @NotNull @PathParam("urlAutor") String urlAutor) {
		try {
			InformacionAutor infoAutor = autorController.findInformacion(urlAutor);
			if (infoAutor == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.ok().entity(infoAutor).build();
		} catch (AutorException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Post
	public Response crearFavoritos() {
		return Response.ok().build();
	}

	// Get
	public Response findFavoritos(String identificador) {
		return Response.ok().build();
	}

	// Delete // Put // Patch
	public Response deleteAutorFavoritos(String identificador, String urlAutor) {
		return Response.ok().build();
	}

	// Post put path
	public Response addAutorFavoritos(String identificador, String urlAutor) {
		return Response.ok().build();
	}

	// Delete
	public Response deleteBBDD() {
		return Response.ok().build();
	}
}
