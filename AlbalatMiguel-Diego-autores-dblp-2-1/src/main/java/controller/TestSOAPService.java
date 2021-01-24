package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import clientesoap.DbplWebServiceSOAP;
import clientesoap.DbplWebServiceSOAPImplService;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

public class TestSOAPService {

	public static void main(String[] args) throws UnsupportedEncodingException, ResourceNotFoundException, DbplException {
		DbplWebServiceSOAPImplService servicio = new DbplWebServiceSOAPImplService();
		DbplWebServiceSOAP puerto = servicio.getDbplWebServiceSOAPImplPort();
		
		System.out.println("Realizando la busqueda de autores con el termino: 'berners'");
		Autores autores = puerto.findAutores("berners");
		System.out.println("El resultado de la busqueda es el siguiente: ");
		for (int i = 0; i < autores.getAutor().size(); i++) {
			System.out.println("\t "+i+": "+autores.getAutor().get(i).getNombre());
		}
		System.out.println("Recuperando información del autor con url https://dblp.org/pid/b/TimBernersLee");
		InformacionAutor infoAutor = puerto.findInformacion("https://dblp.org/pid/b/TimBernersLee");
		System.out.println("Información del autor: ");
		System.out.println("\tNombre: "+infoAutor.getNombreCompleto()+"\n\tHijos: "+infoAutor.getInformacionPersonal().getHijos());
		System.out.println("Creando documento de autores favoritos...");
		String idDocFav = puerto.crearFavoritos();
		System.out.println("Id del documento creado: " + idDocFav);
		System.out.println("Añadiendo la url https://dblp.org/pid/b/TimBernersLee al documento con id " + idDocFav);
		puerto.addAutorFavoritos(idDocFav, "https://dblp.org/pid/b/TimBernersLee");
		System.out.println("Añadiendo la url https://dblp.org/pid/b/TimHook al documento con id " + idDocFav);
		puerto.addAutorFavoritos(idDocFav, "https://dblp.org/pid/b/TimHook");
		System.out.println("Añadiendo la url https://dblp.org/pid/b/TimBradey al documento con id " + idDocFav);
		puerto.addAutorFavoritos(idDocFav, "https://dblp.org/pid/b/TimBradey");
		System.out.println("Eliminando la url https://dblp.org/pid/b/TimBradey"+" del documento con id "+ idDocFav);
		puerto.deleteAutorFavoritos(idDocFav, "https://dblp.org/pid/b/TimBernersLee");
		System.out.println("Recuperando documento de autores favoritos con id: " + idDocFav);
		Favoritos doc = puerto.findFavoritos(idDocFav);
		System.out.println("Mostrando url del documento de autores favoritos con id: " + idDocFav);
		for (int i = 0; i < doc.getUrlAutor().size(); i++) {
			System.out.println("\t "+i+": "+doc.getUrlAutor().get(i));
		}
		System.out.println("Borrando la BBDD completa.");
		System.out.println(puerto.deleteBBDD() ? "Borrando completado con exito" : "Ha ocurrido algun problema borrando la BBDD");
		

		
		System.out.println("Recuperando documento de autores favoritos con id: " + idDocFav);

	}
}
