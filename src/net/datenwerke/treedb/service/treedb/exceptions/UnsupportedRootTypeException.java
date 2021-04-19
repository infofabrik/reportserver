package net.datenwerke.treedb.service.treedb.exceptions;


public class UnsupportedRootTypeException extends TreeDBRuntimeException {

	public UnsupportedRootTypeException(Class<?> type){
		super(type.getCanonicalName() + " is not a valid root type."); //$NON-NLS-1$
	}
}
