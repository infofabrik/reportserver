package net.datenwerke.treedb.service.treedb.exceptions;


public class TreeDBCorruptException extends TreeDBRuntimeException {

	public TreeDBCorruptException(){
		super("It seems as if your database is corrupted. Please run manual checks."); //$NON-NLS-1$
	}
}
