package net.datenwerke.scheduler.service.scheduler.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.hooks.MonitorActionExecution;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class MonitorActionExecutionAdapter implements MonitorActionExecution {

	@Override
	public void notifyOfExecution(AbstractAction action)  {
	}


	@Override
	public void actionExecutedSuccessfully(AbstractAction action)  {
	}


	@Override
	public void actionExecutionFailed(AbstractAction action, Exception e)  {
	}



}
