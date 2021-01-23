package controller;

@SuppressWarnings("serial")
public class AutorException extends Exception {

	public AutorException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public AutorException(String msg) {
		super(msg);		
	}
}
