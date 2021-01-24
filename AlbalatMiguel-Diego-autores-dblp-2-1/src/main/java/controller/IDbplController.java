package controller;

import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

public interface IDbplController {

	/*
	 * Función encargada de retornar los autores junto con los enlaces a los registro de la API DBLP.
	 * Devuelve una lista de autores encontrados. 
	 * @Param autor - Nombre del autor a buscar
	 * @Return Autores
	 * @Throws DbplException, ResourceNotFoundException   
	 */
	public Autores findAutores(String autor) throws DbplException, ResourceNotFoundException;
	
	/*
	 * Función para recuperar de DBLP y otras fuentes información adicional del autor que corresponda con la url pasado como parametro.
	 * Devuelve la información del autor si es que la hubiera.
	 * @Param urlAutor - URL en dblp del autor a buscar la información
	 * @Return InformacionAutor
	 * @Throws DbplException, ResourceNotFoundException   
	 **/
	public InformacionAutor findInformacion(String urlAutor) throws DbplException, ResourceNotFoundException;
	
	/*
	 * Función para crear un documento para almacenar url de autores favoritos.
	 * Devuelve el identificador del documento creado. 
	 * @Return String
	 * @Throws DbplException 
	 */
	public String crearFavoritos() throws DbplException;
	
	/*
	 * Función para recuperar un documento de favoritos mediante el identificador.
	 * Devuelve el documento de autores favoritos. 
	 * @Param identificador - Codigo para identificar el documento de autores favoritos 
	 * @Return Favoritos
	 * @Throws DbplException, ResourceNotFoundException 
	 */
	public Favoritos findFavoritos(String identificador) throws DbplException, ResourceNotFoundException;
	
	/*
	 * Funcion para eliminar una url del documento de favoritos. 
	 * Devuelve un booleano indicando si se ha eliminado o no.
	 * @Param identificador - Codigo para identificar el documento de autores favoritos 
	 * @Param urlAutor
	 * @Return boolean
	 * @Throws DbplException, ResourceNotFoundException 
	 **/
	public boolean deleteAutorFavoritos(String identificador, String urlAutor) throws DbplException, ResourceNotFoundException;
	
	/*
	 * Funcion para añadir una url del documento de favoritos. 
	 * Devuelve el documento de favoritos.
	 * @Param identificador - Codigo para identificar el documento de autores favoritos 
	 * @Param urlAutor
	 * @Return Favoritos
	 * @Throws DbplException, ResourceNotFoundException 
	 **/	
	public Favoritos addAutorFavoritos(String identificador, String urlAutor) throws DbplException, ResourceNotFoundException;
	
	/*
	 * Operación encarga de eliminar todos los xml que conforman la BBDD 
	 * @Return boolean
	 * @Throws DbplException, ResourceNotFoundException 
	 */
	public boolean deleteBBDD() throws DbplException;
	
}
