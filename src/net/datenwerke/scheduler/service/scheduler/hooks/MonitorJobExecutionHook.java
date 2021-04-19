package net.datenwerke.scheduler.service.scheduler.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

@HookConfig
public interface MonitorJobExecutionHook extends Hook {

	void notifyOfExecution(AbstractJob reportExecuteJob);

	void jobExecutedSuccessfully(AbstractJob reportExecuteJob);

	/***
	 * 
	 * be careful. if the action ended abnormally the transaction will be rolled back. so if you plan
	 * to write something into the database, make sure to get a new transaction.
	 */
	void jobExecutionFailed(AbstractJob reportExecuteJob,
			Exception e);

}
