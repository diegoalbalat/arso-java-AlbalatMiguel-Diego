import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import modelo.Favoritos;
import modelo.InformacionAutor;
import modelo.atom.FeedType;
import modelo.hal.AutoresResource;

@TestMethodOrder(OrderAnnotation.class)
public class DbplServiceRestTest {
	private static Client cliente;
	private static WebResource recurso;
	private static String URL_SERVICIO = "http://localhost:8080/api/dblp";
	public final static String bernersUrl = "https://dblp.org/pid/b/TimBernersLee";
	public static String idDocFav = "";

	@BeforeAll
	public static void setUp() {
		cliente = Client.create();
	}

	@Test
	@Order(1)
	public void findAutoresAtomTest() {
		String path = "/autores";
		recurso = cliente.resource(URL_SERVICIO + path).queryParam("autor", "berners");
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_XML).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 200);
		FeedType busqueda = respuesta.getEntity(FeedType.class);
		assertNotNull(busqueda);
		assertTrue(busqueda.getIdOrLinkOrTitle().size() == 8);
	}

	@Test
	@Order(2)
	public void findAutoresHalTest() {
		String path = "/autores";
		recurso = cliente.resource(URL_SERVICIO + path).queryParam("autor", "berners");
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 200);
		AutoresResource busqueda = respuesta.getEntity(AutoresResource.class);
		assertNotNull(busqueda);
		assertTrue(busqueda.getCount() == 5);
	}

	@Test
	@Order(3)
	public void findAutoresNotFoundTest() {
		String path = "/autores";
		recurso = cliente.resource(URL_SERVICIO + path).queryParam("autor", "iefjoiwejfijweifojweijfiowejf");
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 404);
	}

	@Test
	@Order(4)
	public void findInfoAutorTest() {
		String path = null;
		try {
			path = "/autores/" + URLEncoder.encode(bernersUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 200);
		InformacionAutor infoAutor = respuesta.getEntity(InformacionAutor.class);
		assertNotNull(infoAutor);

	}

	@Test
	@Order(5)
	public void findInfoAutorNotFoundTest() {
		String path = null;
		try {
			path = "/autores/" + URLEncoder.encode("https://dblp.org/pid/b/TimBernersLeijiwjefiwje", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 404);
	}

	@Test
	@Order(6)
	public void crearFavoritosTest() {
		String path = "/favoritos";

		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("POST", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 201);
		String[] splits = respuesta.getHeaders().get("Location").get(0).split("/");
		idDocFav = splits[splits.length - 1];
		assertTrue(!StringUtils.isEmpty(idDocFav));

	}

	@Test
	@Order(7)
	public void getFavoritosTest() {
		String path = "/favoritos/" + idDocFav;
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 200);
		Favoritos fav = respuesta.getEntity(Favoritos.class);
		assertNotNull(fav);
	}

	@Test
	@Order(8)
	public void getFavoritosNotFoundTest() {
		String path = "/favoritos/" + "9594395894848548583475834";
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 404);
	}

	@Test
	@Order(9)
	public void addAutorFavoritos() {
		String path = "/favoritos/" + idDocFav + "/entradas";
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).type("application/json")
				.post(ClientResponse.class, bernersUrl);
		assertTrue(respuesta.getStatus() == 201);
	}

	@Test
	@Order(10)
	public void addAutorNotFoundFavoritos() {
		String path = "/favoritos/" + "9382390872347" + "/entradas";
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).type("application/json")
				.post(ClientResponse.class, bernersUrl);
		assertTrue(respuesta.getStatus() == 404);

	}

	@Test
	@Order(11)
	public void deleteAutorFavoritos() {
		String path = null;
		try {
			path = "/favoritos/" + idDocFav + "/entradas/" + URLEncoder.encode(bernersUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("DELETE", ClientResponse.class);

		assertTrue(respuesta.getStatus() == 204);
	}

	@Test
	@Order(12)
	public void deleteAutorNotFoundFavoritos() {
		String path = "/favoritos/9382390872347/entradas/394823984923489234";
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("DELETE", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 404);

	}

	@Test
	@Order(13)
	public void deleteBBDDTest() {
		String path = "/";
		recurso = cliente.resource(URL_SERVICIO + path);
		ClientResponse respuesta = recurso.accept(MediaType.APPLICATION_JSON).method("DELETE", ClientResponse.class);
		assertTrue(respuesta.getStatus() == 204);
	}
}
