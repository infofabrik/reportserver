package net.datenwerke.rs.scheduler.service.scheduler.exceptions;

import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class InvalidConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706131425574517662L;

	public InvalidConfigurationException(String msg) {
		super(msg);
	}

	public InvalidConfigurationException(ActionNotSupportedException e) {
		super(e);
	}

}
