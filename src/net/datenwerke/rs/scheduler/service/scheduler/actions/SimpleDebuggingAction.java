package net.datenwerke.rs.scheduler.service.scheduler.actions;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

public class SimpleDebuggingAction extends AbstractAction {

	@Override
	public void execute(AbstractJob abstractJob) {
		System.out.println("executed job: " + abstractJob.getClass().getName()); //$NON-NLS-1$
	}


}
