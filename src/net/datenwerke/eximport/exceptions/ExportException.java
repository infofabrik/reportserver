package net.datenwerke.eximport.exceptions;

public class ExportException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5943762831800696410L;

	public ExportException(){
		super("An error occured during export.");
	}
	
	public ExportException(String msg){
		super(msg);
	}
	
	public ExportException(Throwable cause){
		super("An error occured during export: " + cause.getMessage(), cause);
	}
	
	public ExportException(String msg, Throwable cause){
		super(msg, cause);
	}
}
