package controller;

import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

public interface AutoresController {

	/*
	 * Función encargada de retornar los autores junto con los enlaces a los registro de la API DBLP.
	 * @Param autor - Nombre del autor a buscar
	 * 		Tipo: String   
	 */
	public Autores findAutores(String autor);
	
	/*
	 * Función para recuperar de DBLP y otras fuentes información adicional del autor que corresponda con la url pasado como parametro.
	 * @Param urlAutor - URL en dblp del autor a buscar la información
	 * 		Tipo: String   
	 **/
	public InformacionAutor findInformacion(String urlAutor);
	
	public String crearFavoritos();
	
	public Favoritos findFavoritos(String identificador);
	
	public boolean deleteAutorFavoritos(String identificador, String urlAutor);
	
	public Favoritos addAutorFavoritos(String identificador, String urlAutor);
	
}
