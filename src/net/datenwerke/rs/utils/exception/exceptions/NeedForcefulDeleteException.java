package net.datenwerke.rs.utils.exception.exceptions;

public class NeedForcefulDeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6385792963943956536L;

	public NeedForcefulDeleteException(String msg){
		super (msg);
	}
	
	public NeedForcefulDeleteException(Throwable t){
		super(t);
	}
}
