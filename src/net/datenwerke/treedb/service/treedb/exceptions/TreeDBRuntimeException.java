package net.datenwerke.treedb.service.treedb.exceptions;

public class TreeDBRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 435216351359237288L;

	public TreeDBRuntimeException(){
		super();
	}
	
	public TreeDBRuntimeException(String msg){
		super(msg);
	}
}
