package controller;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);		
	}
}
