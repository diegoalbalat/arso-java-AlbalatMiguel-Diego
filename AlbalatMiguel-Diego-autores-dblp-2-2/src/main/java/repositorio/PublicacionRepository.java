package repositorio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;

import modelo.Publicacion;

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
	}

	public PublicacionRepository(MongoCollection<Document> tareas) {
		this.publicaciones = tareas;
		mapper = new ObjectMapper();
	}

	@Override
	public Publicacion guardarPublicacion(Publicacion nueva) {
		Document doc = new Document().append("id", nueva.getId()).append("usuario", nueva.getUsuario())
				.append("autor", nueva.getAutor()).append("urlAutor", nueva.getUrlAutor())
				.append("urlPubliacionDBPL", nueva.getUrlPubliacionDBPL());
		publicaciones.insertOne(doc);
		return nueva;
	}

	@Override
	public List<Publicacion> obtenerPublicacionesByUsuario(String usuario) {
		Document filtroUsuario = new Document().append("usuario", usuario);
		Set<String> publicacionesSet= new HashSet<String>();
		List<Publicacion> result = new LinkedList<Publicacion>();
		for( Document publicacion : publicaciones.find(filtroUsuario)){
			
			if (!publicacionesSet.contains((String)publicacion.get("urlPubliacionDBPL"))){
				publicacionesSet.add((String)publicacion.get("urlPubliacionDBPL"));
				Publicacion publi = mapper.convertValue(publicacion, Publicacion.class);
				result.add(publi);
			}
		}
		
		return result;
	}

	@Override
	public List<Publicacion> obtenerPublicacionesByAutorUrl(String urlAutor) {
		Document filtroAutor = new Document().append("urlAutor", urlAutor);
		Set<String> publicacionesSet= new HashSet<String>();
		List<Publicacion> result = new LinkedList<Publicacion>();
		for( Document publicacion : publicaciones.find(filtroAutor)){
			
			if (!publicacionesSet.contains((String)publicacion.get("urlPubliacionDBPL"))){
				publicacionesSet.add((String)publicacion.get("urlPubliacionDBPL"));
				Publicacion publi = mapper.convertValue(publicacion, Publicacion.class);
				result.add(publi);
			}
		}
		
		return result;
	}

}
