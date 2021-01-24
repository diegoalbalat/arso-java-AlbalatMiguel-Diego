package modelo.hal;

import java.util.LinkedList;
import java.util.List;

public class Embedded {
	protected List<AutorResource> autores;
	public Embedded() {
		
	}
	public Embedded(List<AutorResource> autores) {
		super();
		this.autores = autores;
	}

	public List<AutorResource> getAutores() {
		if(autores == null) {
			autores = new LinkedList<AutorResource>();
		}
		return autores;
	}

	public void setAutores(List<AutorResource> autores) {
		this.autores = autores;
	}
	
}
