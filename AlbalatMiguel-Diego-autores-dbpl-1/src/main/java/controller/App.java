package controller;

import modelo.Autor;
import modelo.Autores;

public class App {

	public static void main(String[] args) {
		AutoresControllerImpl a = new AutoresControllerImpl();
//		Autores autores = a.findAutores("berners");
//		for (Autor autor : autores.getAutor()) {
//			System.out.println(autor.getId() + " "+autor.getNombre()+ " "+autor.getUrl());
//		}
		a.findInformacion("https://dblp.org/pid/b/TimBernersLee");
	}

}
