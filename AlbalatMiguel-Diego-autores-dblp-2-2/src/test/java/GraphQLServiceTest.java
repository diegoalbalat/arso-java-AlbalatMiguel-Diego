import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class GraphQLServiceTest {

	private static String endpoint = "http://localhost:8081/graphql";
	private static String UUID = "";
	@BeforeAll
	public static void setUp() {
		UUID = ""+Date.from(Instant.now()).hashCode();
	}

	@Test
	@Order(1)
	public void registrarPublicacionTest() {
		HttpURLConnection cliente;
		try {
			cliente = (HttpURLConnection) new URL(endpoint).openConnection();
			cliente.setRequestMethod("POST");
			cliente.setDoOutput(true);
			cliente.setRequestProperty("Content-Type", "application/json");
			PrintWriter writer = new PrintWriter(cliente.getOutputStream());
			System.out.println(UUID);
			writer.print(
					"{\"query\":\"mutation { registrarPublicacion(usuario:\\\"diego@um.es"+UUID+"\\\" , autor:\\\"TimBerners-Lee"+UUID+"\\\" , urlAutor:\\\"https://dblp.org/pid/b/TimBernersLee"+UUID+"\\\" , urlPublicacionDBPL:\\\"https://dblp.org/rec/journals/rfc/rfc1631\\\" ) {usuario autor urlAutor urlPublicacionDBPL}}\"}");
			writer.flush();
			writer.close();
			int codigoRespuesta = cliente.getResponseCode();

			JsonReader jsonReader = Json.createReader(cliente.getInputStream());
			JsonObject obj = jsonReader.readObject().get("data").asJsonObject().get("registrarPublicacion")
					.asJsonObject();
			String usuario = obj.getString("usuario");
			String autor = obj.getString("autor");
			String urlAutor = obj.getString("urlAutor");
			String urlPublicacionDBPL = obj.getString("urlPublicacionDBPL");

			assertTrue(codigoRespuesta == 200 && usuario != null && !usuario.isEmpty() && autor != null
					&& !autor.isEmpty() && urlAutor != null && !urlAutor.isEmpty() && urlPublicacionDBPL != null
					&& !urlPublicacionDBPL.isEmpty());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	public void recuperarPublicacionesByUsuarioTest() {
		try {
			String consulta = "query {  obtenerPublicacionesUsuario(usuario: \"diego@um.es"+UUID+"\") {    autor    urlAutor    urlPublicacionDBPL  }}";
			String consultaCodificada = URLEncoder.encode(consulta, "UTF-8");
			String urlString = "http://localhost:8081/graphql?query=" + consultaCodificada;
			URL url = new URL(urlString);

			JsonReader jsonReader = Json.createReader(url.openStream());
			JsonArray listaPublicaciones = jsonReader.readObject().get("data").asJsonObject()
					.get("obtenerPublicacionesUsuario").asJsonArray();

			assertTrue(listaPublicaciones.size() != 0);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	public void recuperarPublicacionesByAutorTest() {
		try {
			String consulta = "query {  obtenerPublicacionesAutor(urlAutor: \"https://dblp.org/pid/b/TimBernersLee"+UUID+"\") {    autor    urlAutor    urlPublicacionDBPL  }}";
			String consultaCodificada = URLEncoder.encode(consulta, "UTF-8");
			String urlString = "http://localhost:8081/graphql?query=" + consultaCodificada;
			URL url = new URL(urlString);

			JsonReader jsonReader = Json.createReader(url.openStream());
			JsonArray listaPublicaciones = jsonReader.readObject().get("data").asJsonObject()
					.get("obtenerPublicacionesAutor").asJsonArray();;

			assertTrue(listaPublicaciones.size() == 1);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
