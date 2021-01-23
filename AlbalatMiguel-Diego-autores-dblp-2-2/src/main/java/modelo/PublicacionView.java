package modelo;

import java.io.Serializable;

public class PublicacionView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String autor;
	
	private String urlAutor;
	
	private String urlPublicacionDBPL;

	public PublicacionView() {
		super();
	}
	
	public PublicacionView( String autor, String urlAutor, String urlPublicacionDBPL) {
		super();
		this.autor = autor;
		this.urlAutor = urlAutor;
		this.urlPublicacionDBPL = urlPublicacionDBPL;
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

	public String getUrlPublicacionDBPL() {
		return urlPublicacionDBPL;
	}

	public void setUrlPublicacionDBPL(String urlPubliacionDBPL) {
		this.urlPublicacionDBPL = urlPubliacionDBPL;
	}
	
	

}
