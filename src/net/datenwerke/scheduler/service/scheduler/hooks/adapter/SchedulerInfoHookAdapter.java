package net.datenwerke.scheduler.service.scheduler.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerInfoHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class SchedulerInfoHookAdapter implements SchedulerInfoHook {

	@Override
	public void beforeJobSchedule(AbstractJob job, AbstractTrigger trigger)  {
	}


	@Override
	public void jobScheduled(AbstractJob job, AbstractTrigger trigger)  {
	}


	@Override
	public void beforeJobRescheduled(AbstractJob job, AbstractTrigger trigger, AbstractJob previous)  {
	}


	@Override
	public void beforeJobUnschedule(AbstractJob job)  {
	}


	@Override
	public void jobRescheduled(AbstractJob job, AbstractTrigger trigger)  {
	}


	@Override
	public void jobUnscheduled(AbstractJob job)  {
	}


	@Override
	public void beforeJobRemove(AbstractJob job)  {
	}


	@Override
	public void jobRemoved(AbstractJob job)  {
	}



}
