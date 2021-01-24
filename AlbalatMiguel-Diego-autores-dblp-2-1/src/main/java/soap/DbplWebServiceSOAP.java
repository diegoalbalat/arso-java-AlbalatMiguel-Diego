package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import controller.IDbplController;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DbplWebServiceSOAP extends IDbplController {

	@WebMethod
	public Autores findAutores(String autor) ;
	
	@WebMethod
	public InformacionAutor findInformacion(String urlAutor) ;
	
	@WebMethod
	public String crearFavoritos();
	
	@WebMethod
	public Favoritos findFavoritos(String identificador) ;
	
	@WebMethod
	public boolean deleteAutorFavoritos(String identificador, String urlAutor) ;
	
	@WebMethod
	public Favoritos addAutorFavoritos(String identificador, String urlAutor);
	
	@WebMethod
	public boolean deleteBBDD();
}
