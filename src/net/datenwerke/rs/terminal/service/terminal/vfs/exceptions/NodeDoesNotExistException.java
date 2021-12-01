package net.datenwerke.rs.terminal.service.terminal.vfs.exceptions;

import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;

public class NodeDoesNotExistException extends VFSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7017978184337802595L;

	public NodeDoesNotExistException(){
		super(TerminalMessages.INSTANCE.nodeDoesNotExistException());
	}
	
	public NodeDoesNotExistException(String msg){
		super(msg);
	}
}
