package controller;

import modelo.Autores;

public interface AutoresController {

	/*
	 * Función encargada de retornar los autores junto con los enlaces a los registro de la API DBLP.   
	 */
	public Autores findAutores(String autor);
	
}
