package net.datenwerke.eximport.exceptions;


public class IllegalImportConfigException extends ImportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7159839444159564248L;

	public IllegalImportConfigException(){
		super("Configuration is illegal.");
	}
	
	public IllegalImportConfigException(String msg){
		super(msg);
	}
	
	public IllegalImportConfigException(Throwable cause){
		super("Configuration is illegal.", cause);
	}
	
	public IllegalImportConfigException(String msg, Throwable cause){
		super(msg, cause);
	}

}
