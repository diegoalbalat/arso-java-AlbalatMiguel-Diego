package modelo;

import java.io.Serializable;

public class Publicacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String usuario;
	
	private String autor;
	
	private String urlAutor;
	
	private String urlPubliacionDBPL;

	public Publicacion() {
		super();
	}
	
	public Publicacion(String usuario, String autor, String urlAutor, String urlPubliacionDBPL) {
		super();
		this.usuario = usuario;
		this.autor = autor;
		this.urlAutor = urlAutor;
		this.urlPubliacionDBPL = urlPubliacionDBPL;
	}

	public String getId() {
		return id;
	}

	public void setId(String _id) {
		this.id = _id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getUrlAutor() {
		return urlAutor;
	}

	public void setUrlAutor(String urlAutor) {
		this.urlAutor = urlAutor;
	}

	public String getUrlPubliacionDBPL() {
		return urlPubliacionDBPL;
	}

	public void setUrlPubliacionDBPL(String urlPubliacionDBPL) {
		this.urlPubliacionDBPL = urlPubliacionDBPL;
	}
	
	

}
