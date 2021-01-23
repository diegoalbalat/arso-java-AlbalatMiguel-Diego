package controller;

import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

public interface AutoresController {

	/*
	 * Función encargada de retornar los autores junto con los enlaces a los registro de la API DBLP.
	 * Devuelve una lista de autores encontrados. 
	 * @Param autor - Nombre del autor a buscar
	 * 		Tipo: String   
	 */
	public Autores findAutores(String autor) throws AutorException;
	
	/*
	 * Función para recuperar de DBLP y otras fuentes información adicional del autor que corresponda con la url pasado como parametro.
	 * Devuelve la información del autor si es que la hubiera.
	 * @Param urlAutor - URL en dblp del autor a buscar la información
	 * 		Tipo: String   
	 **/
	public InformacionAutor findInformacion(String urlAutor) throws AutorException;
	
	/*
	 * Función para crear un documento para almacenar url de autores favoritos.
	 * Devuelve el identificador del documento creado. 
	 */
	public String crearFavoritos() throws AutorException;
	
	/*
	 * Función para recuperar un documento de favoritos mediante el identificador.
	 * Devuelve el documento de autores favoritos. 
	 * @Param identificador - Codigo para identificar el documento de autores favoritos 
	 * 		Tipo: String 
	 */
	public Favoritos findFavoritos(String identificador) throws AutorException;
	
	/*
	 * Funcion para eliminar una url del documento de favoritos. 
	 * Devuelve un booleano indicando si se ha eliminado o no.
	 * @Param identificador - Codigo para identificar el documento de autores favoritos
	 * 		Tipo: String   
	 * @Param urlAutor
	 **/
	public boolean deleteAutorFavoritos(String identificador, String urlAutor) throws AutorException;
	
	/*
	 * Funcion para añadir una url del documento de favoritos. 
	 * Devuelve el documento de favoritos.
	 * @Param identificador - Codigo para identificar el documento de autores favoritos
	 * 		Tipo: String   
	 * @Param urlAutor
	 **/	
	public Favoritos addAutorFavoritos(String identificador, String urlAutor) throws AutorException;
	
	/*
	 * Operación encarga de eliminar todos los xml que conforman la BBDD 
	 */
	public boolean deleteBBDD() throws AutorException;
	
}
