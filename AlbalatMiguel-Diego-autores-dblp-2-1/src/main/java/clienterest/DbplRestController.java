package clienterest;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import controller.DbplException;
import controller.DbplControllerImpl;
import controller.IDbplController;
import controller.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;
import modelo.atom.FeedType;
import modelo.hal.AutoresResource;
import services.EntidadRespuestaMapperServiceImpl;
import services.IEntidadRespuestaMapperService;

@Path("dblp")
@Produces(MediaType.APPLICATION_JSON)
@Api(produces = "application/xml, application/json", value = "Operations pertaining to manager blood donors in the application")
public class DbplRestController {

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	private IDbplController autorController = DbplControllerImpl.getUnicaInstancia();

	private IEntidadRespuestaMapperService mapperService = EntidadRespuestaMapperServiceImpl.getUnicaInstancia();

	@GET
	@Path("/autores")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Devuelve un listado de tipo autor encontrado al filtrar por el término de búsqueda indicado.", response = Response.class)
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
			// Comprobar mediaType solicitado y devolver según corresponda
			String mediaType = headers.getRequestHeader("Accept").get(0);
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.queryParam("autor", autor);
			URI uri = builder.build();
			if (mediaType.equals("application/xml")) {

				FeedType respuesta = mapperService.autoresToAtom(uri, autores, autor);
				return Response.ok().entity(respuesta).build();
			} else {
				AutoresResource respuesta = mapperService.autoresToHal(autores, uri, autor);
				return Response.ok().entity(respuesta).build();
			}

		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

	@GET
	@Path("/autores/{urlAutor}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Operación que recupera de DBPL, GB y DBPedia la información del autor pasado como párametro siendo representado por su url en DBPL.", response = InformacionAutor.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Devuelve la información recopilada del autor."),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "La busqueda no ha obtenido ninguna información."),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response findInformacionAutor(
			@ApiParam(name = "urlAutor", value = "Url de identificación del autor", required = true) @NotNull @PathParam("urlAutor") String urlAutor) {
		if(StringUtils.isEmpty(urlAutor)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			InformacionAutor infoAutor = autorController.findInformacion(urlAutor);
			return Response.ok().entity(infoAutor).build();
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("/favoritos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Crear un nuevo documento de autores favoritos y devuelve su identificador.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Devuelve la localización del documento de favoritos creado."),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"), })
	public Response crearFavoritos() {
		try {
			String id = autorController.crearFavoritos();
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(id);
			URI nuevaURL = builder.build();
			System.out.println(nuevaURL.toString());
			return Response.created(nuevaURL).build();
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/favoritos/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Operación que recupera de la BBDD el documento de autores favoritos asociado con el identificador pasado  como parámetro.", response = InformacionAutor.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Devuelve el documento de favoritos asociado al identificador."),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No existe un documento de favoritos con el identificador."),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response findFavoritos(
			@ApiParam(name = "id", value = "Identificador del documento de favoritos objetivo.", required = true) @NotNull @PathParam("id") String id) {
		if(StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			Favoritos fav = autorController.findFavoritos(id);
			return Response.ok().entity(fav).build();
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/favoritos/{identificador}/entradas/{urlAutor}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Elimina una entrada especificada por urlAutor del documento de autores favoritos identificado por identificador.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Entrada eliminada correctamente del documento de favoritos."),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No existe el documento o la entrada especificada"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response deleteAutorFavoritos(
			@ApiParam(name = "identificador", value = "Identificador del documento de favoritos objetivo.", required = true) @NotNull @PathParam("identificador") String identificador,
			@ApiParam(name = "urlAutor", value = "Url de identificación del autor", required = true) @NotNull @PathParam("urlAutor") String urlAutor) {
		if(StringUtils.isEmpty(urlAutor) || StringUtils.isEmpty(identificador)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			if (autorController.deleteAutorFavoritos(identificador, urlAutor)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			return Response.status(Response.Status.NOT_FOUND).build(); // No sucede nunca
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("/favoritos/{identificador}/entradas")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Crear una nueva entrada con la url del autor en el documento de autores favoritos.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Entrada creada correctamente en el documento de favoritos."),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No existe el documento especificado"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha realizado la petición con un formato correcto") })
	public Response addAutorFavoritos(
			@ApiParam(name = "identificador", value = "Identificador del documento de favoritos objetivo.", required = true) @NotNull @PathParam("identificador") String identificador,
			@ApiParam(name = "urlAutor", value = "Url de identificación del autor", required = true) @NotNull String urlAutor) {
		if(StringUtils.isEmpty(urlAutor) || StringUtils.isEmpty(identificador)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			autorController.addAutorFavoritos(identificador, urlAutor);
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(String.valueOf(urlAutor));
			URI nuevaURL = builder.build();
			return Response.created(nuevaURL).build();
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Elimina toda la información de la BBDD, es decir, los ficheros xml de los autores y los ficheros de favoritos.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "BBDD eliminada correctamente."),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Se ha producido un error") })
	public Response deleteBBDD() {
		try {
			if (autorController.deleteBBDD() == false) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (DbplException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

		}
	}

}
