package net.datenwerke.eximport.exceptions;

public class ImportException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5943762831800696410L;

	public ImportException(){
		super("An error occured during import.");
	}
	
	public ImportException(String msg){
		super(msg);
	}
	
	public ImportException(Throwable cause){
		super("An error occured during import: " + cause.getMessage(), cause);
	}
	
	public ImportException(String msg, Throwable cause){
		super(msg, cause);
	}
}
