package controller;

import modelo.Favoritos;

public class App {

	public static void main(String[] args) {
		AutoresControllerImpl a = new AutoresControllerImpl();
//		Autores autores = a.findAutores("berners");
//		for (Autor autor : autores.getAutor()) {
//			System.out.println(autor.getId() + " "+autor.getNombre()+ " "+autor.getUrl());
//		}
		a.findInformacion("https://dblp.org/pid/b/TimBernersLee");
		String id = a.crearFavoritos();
		Favoritos hola = a.addAutorFavoritos(id, "https://dblp.org/pid/b/TimBernersLee");
		Favoritos temp = a.findFavoritos(id);
		boolean status = a.deleteAutorFavoritos(id, "https://dblp.org/pid/b/TimBernersLee");
		Favoritos temp2 = a.addAutorFavoritos(id, "https://dblp.org/pid/b/TimBernersLeo");
	}

}
