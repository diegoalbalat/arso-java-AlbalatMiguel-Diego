
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import controller.AutorException;
import controller.IAutoresController;
import controller.AutoresControllerImpl;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;
import modelo.InformacionPersonal;
import modelo.Libro;

@TestMethodOrder(OrderAnnotation.class)
public class AutorControllerTest {
	private static Autores autores;
	private static InformacionAutor infoAutor;
	private static IAutoresController controlador;
	private static String idFavoritos;
	private static Favoritos favoritos;

	public final static String autorSearch = "berners";
	public final static String autorSearch2 = "timothy";
	public final static String bernersUrl = "https://dblp.org/pid/b/TimBernersLee";

	@BeforeAll
	public static void setUp() {
		idFavoritos = "";
		autores = null;
		infoAutor = null;
		controlador = new AutoresControllerImpl();
		favoritos = null;
		try {
			controlador.deleteBBDD();
		} catch (AutorException e) {
			e.printStackTrace();
		}

	}

	@Test
	@Order(1)
	public void findAutoresReturnOutputTest() {
		try {
			autores = controlador.findAutores(autorSearch2);
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(autores != null);
	}

	@Test
	@Order(2)
	public void findAutoresReturnCorrectOutput2Test() {
		try {
			autores = controlador.findAutores("fhwohgfiwhugheoughwuoghcqejifhincqp");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(autores.getAutor().size() == 0);
	}

	@Test
	@Order(3)
	public void findAutoresReturnCorrectOutputTest() {
		try {
			autores = controlador.findAutores(autorSearch);
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(autores.getAutor().size() == 5);
	}

	@Test
	@Order(4)
	public void findInfoAutorTest() {
		try {
			infoAutor = controlador.findInformacion(bernersUrl);
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(infoAutor != null);
	}

	@Test
	@Order(5)
	public void infoAutorDBPLTest() {
		boolean nombreCompleto = infoAutor.getNombreCompleto().equals("Tim Berners-Lee");
		boolean afiliacion = infoAutor.getAfiliacion().getAfiliacionPrimaria()
				.equals("Massachusetts Institiute of Technology, Cambridge, MA, USA")
				&& infoAutor.getAfiliacion().getAfiliacionSecundaria().size() == 2;
		boolean paginasPremios = infoAutor.getPaginasPremios().size() == 2;
		boolean articulosAutor = infoAutor.getArticulosAutor().size() == 55;
		boolean articulosEditor = infoAutor.getArticulosEditor().size() == 12;
		boolean idAutor = infoAutor.getIdAutor().longValue() == 672560912;
		assertTrue(nombreCompleto && afiliacion && paginasPremios && articulosAutor && articulosEditor && idAutor);
	}

	@Test
	@Order(6)
	public void findInfoAutorGBTest() {
		Libro libro = infoAutor.getLibros().get(1);
		boolean librosCount = infoAutor.getLibros().size() == 80;
		boolean libroId = libro.getId().equals("http://www.google.com/books/feeds/volumes/Unp4PwAACAAJ");
		boolean titulo = libro.getTitulo().equals("Weaving the Web");
		boolean descripcion = libro.getDescripcion().length() == 136;
		boolean isbn = libro.getIsbn().size() == 3;
		boolean idioma = libro.getIdioma().equals("EN");
		boolean fecha = new SimpleDateFormat("yyyy-MM-dd").format(libro.getFecha().toGregorianCalendar().getTime())
				.equals("2008-06-26");
		boolean paginas = libro.getPaginas().longValue() == 272;
		assertTrue(librosCount && libroId && titulo && descripcion && isbn && idioma && fecha && paginas);
	}

	@Test
	@Order(7)
	public void findInfoAutorDBPediaTest() {
		InformacionPersonal iPersonal = infoAutor.getInformacionPersonal();
		boolean fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd")
				.format(iPersonal.getFechaNacimiento().toGregorianCalendar().getTime()).equals("1955-06-08");
		boolean hijos = iPersonal.getHijos().longValue() == 2;
		boolean lugarNacimiento = iPersonal.getLugarNacimiento().equals("London, England");
		boolean padres = iPersonal.getPadres().size() == 2;
		assertTrue(fechaNacimiento && hijos && lugarNacimiento && padres);
	}

	@Test
	@Order(8)
	public void crearFavoritosTest() {

		try {
			idFavoritos = controlador.crearFavoritos();
			assertTrue(!idFavoritos.isEmpty());
		} catch (AutorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Order(9)
	public void getFavoritosTest() {
		try {
			favoritos = controlador.findFavoritos(idFavoritos);
			assertTrue(favoritos != null);
		} catch (AutorException e) {
			e.printStackTrace();
		}

	}

	@Test
	@Order(10)
	public void dontGetFavoritosTest() {
		try {
			favoritos = controlador.findFavoritos("363465345343643643663434643");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(favoritos == null);
	}

	@Test
	@Order(11)
	public void addAutorFavoritos() {
		try {
			favoritos = controlador.addAutorFavoritos(idFavoritos, "http://unaurl.com");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(favoritos != null);
	}

	@Test
	@Order(12)
	public void addAutorWrongIdFavoritos() {
		try {
			favoritos = controlador.addAutorFavoritos("UFWHUOFHEU", "http://unaurl.com");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(favoritos == null);

	}

	@Test
	@Order(13)
	public void deleteAutorFavoritos() {
		boolean status = false;
		try {
			status = controlador.deleteAutorFavoritos(idFavoritos, "http://unaurl.com");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(status);
	}

	@Test
	@Order(14)
	public void deleteAutorNoExistenteFavoritos() {
		boolean status = false;
		try {
			status = controlador.deleteAutorFavoritos(idFavoritos, "http://unaurl.com");
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(!status);
	}

	@Test
	@Order(15)
	public void deleteBBDDTest() {
		boolean status = false;
		try {
			status = controlador.deleteBBDD();
		} catch (AutorException e) {
			e.printStackTrace();
		}
		assertTrue(status);
	}
}
