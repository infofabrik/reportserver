package net.datenwerke.treedb.service.treedb.exceptions;

import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.locale.TreeDbMessages;

public class UnsupportedChildException extends TreeDBRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5119598342580086538L;

	public UnsupportedChildException(Class<? extends AbstractNode> parent, Class<? extends AbstractNode> child) {
		super(TreeDbMessages.INSTANCE.exceptionUnsupportedChild(child.getSimpleName(), parent.getSimpleName()));
	}

}
