package modelo.hal;

import java.math.BigInteger;

public class AutorResource {
	public Links _links;
    protected String nombre;
    protected String url;
    protected BigInteger id;
    
    public AutorResource() {
    	
    }
    
    
	public AutorResource(Links _links, String nombre, String url, BigInteger id) {
		super();
		this._links = _links;
		this.nombre = nombre;
		this.url = url;
		this.id = id;
	}


	public Links get_links() {
		return _links;
	}
	public void set_links(Links _links) {
		this._links = _links;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	

    
    
}
