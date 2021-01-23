package repositorio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;

import modelo.Publicacion;
import modelo.PublicacionView;

public class PublicacionRepository implements IPublicacionRepository {

	private MongoCollection<Document> publicaciones;

	private ObjectMapper mapper;

	public static PublicacionRepository unicaInstancia = null;

	public static PublicacionRepository getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new PublicacionRepository();
		}
		return unicaInstancia;
	}

	public PublicacionRepository() {
		this.publicaciones = MongoContext.getCollection();
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	public PublicacionRepository(MongoCollection<Document> tareas) {
		this.publicaciones = tareas;
		mapper = new ObjectMapper();
	}

	@Override
	public Publicacion guardarPublicacion(String usuario, String autor, String urlAutor, String urlPublicacionDBPL) {
		Document doc = new Document().append("usuario",usuario )
				.append("autor", autor).append("urlAutor", urlAutor)
				.append("urlPublicacionDBPL", urlPublicacionDBPL);
		publicaciones.insertOne(doc);
		Publicacion publi = mapper.convertValue(doc, Publicacion.class);
		return publi;
	}

	@Override
	public List<PublicacionView> obtenerPublicacionesByUsuario(String usuario) {
		Document filtroUsuario = new Document().append("usuario", usuario);
		Set<String> publicacionesSet= new HashSet<String>();
		List<PublicacionView> result = new LinkedList<PublicacionView>();
		for( Document publicacion : publicaciones.find(filtroUsuario)){
			
			if (!publicacionesSet.contains((String)publicacion.get("urlPublicacionDBPL"))){
				publicacionesSet.add((String)publicacion.get("urlPublicacionDBPL"));
				PublicacionView publi = mapper.convertValue(publicacion, PublicacionView.class);
				result.add(publi);
			}
		}
		
		return result;
	}

	@Override
	public List<PublicacionView> obtenerPublicacionesByAutorUrl(String urlAutor) {
		Document filtroAutor = new Document().append("urlAutor", urlAutor);
		Set<String> publicacionesSet= new HashSet<String>();
		List<PublicacionView> result = new LinkedList<PublicacionView>();
		for( Document publicacion : publicaciones.find(filtroAutor)){
			
			if (!publicacionesSet.contains((String)publicacion.get("urlPublicacionDBPL"))){
				publicacionesSet.add((String)publicacion.get("urlPublicacionDBPL"));
				PublicacionView publi = mapper.convertValue(publicacion, PublicacionView.class);
				result.add(publi);
			}
		}
		
		return result;
	}

}
