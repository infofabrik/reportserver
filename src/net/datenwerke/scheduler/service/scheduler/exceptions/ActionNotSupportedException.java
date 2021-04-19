package net.datenwerke.scheduler.service.scheduler.exceptions;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

public class ActionNotSupportedException extends Exception {

	public ActionNotSupportedException(AbstractJob abstractJob,
			AbstractAction action) {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656568142728605278L;

}
