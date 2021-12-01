package net.datenwerke.treedb.service.treedb.exceptions;


public class MultipleRootException extends TreeDBRuntimeException {

	public MultipleRootException(){
		super("Tried to add second root to TreeDBTree that does not allow multiple roots"); //$NON-NLS-1$
	}
	
	public MultipleRootException(Class<?> treeClass){
		super("Tried to add second root to TreeDBTree (" + treeClass.getSimpleName() + ") that does not allow multiple roots"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
