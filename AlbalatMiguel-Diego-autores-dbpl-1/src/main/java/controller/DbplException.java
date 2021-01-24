package controller;

@SuppressWarnings("serial")
public class DbplException extends Exception {

	public DbplException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public DbplException(String msg) {
		super(msg);		
	}
}
