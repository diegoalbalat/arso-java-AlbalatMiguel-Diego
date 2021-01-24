package clientesoap;

import javax.jws.WebService;

import controller.DbplException;
import controller.DbplControllerImpl;
import controller.IDbplController;
import controller.ResourceNotFoundException;
import modelo.Autores;
import modelo.Favoritos;
import modelo.InformacionAutor;

@WebService(endpointInterface = "clientesoap.DbplWebServiceSOAP")
public class DbplWebServiceSOAPImpl implements DbplWebServiceSOAP {

	private IDbplController autorController = DbplControllerImpl.getUnicaInstancia();

	@Override
	public Autores findAutores(String autor) {
		Autores autores = null;
		try {
			autores = autorController.findAutores(autor);
		} catch (DbplException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autores;
	}

	@Override
	public InformacionAutor findInformacion(String urlAutor) {
		InformacionAutor infoAutor = new InformacionAutor();
		try {
			return autorController.findInformacion(urlAutor);
		} catch (DbplException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoAutor;
	}

	@Override
	public String crearFavoritos() {
		try {
			return autorController.crearFavoritos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Favoritos findFavoritos(String identificador) {
		Favoritos fav = null;
		try {
			fav = autorController.findFavoritos(identificador);
			return fav;
		} catch (DbplException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteAutorFavoritos(String identificador, String urlAutor) {
		try {
			return autorController.deleteAutorFavoritos(identificador, urlAutor);
		} catch (DbplException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Favoritos addAutorFavoritos(String identificador, String urlAutor) {
		try {
			return autorController.addAutorFavoritos(identificador, urlAutor);
		} catch (DbplException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteBBDD() {
		try {
			return autorController.deleteBBDD();
		} catch (DbplException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
