package net.datenwerke.scheduler.service.scheduler.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;

@HookConfig
public interface MonitorActionExecution extends Hook{

	void notifyOfExecution(AbstractAction action);

	void actionExecutedSuccessfully(AbstractAction action);

	/***
	 * 
	 * be careful. if the action ended abnormally the transaction will be rolled back. so if you plan
	 * to write something into the database, make sure to get a new transaction.
	 * @param action
	 * @param e
	 */
	void actionExecutionFailed(AbstractAction action,
			Exception e);
}
