package net.datenwerke.scheduler.service.scheduler.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;

@HookConfig
public interface SchedulerInfoHook extends Hook {

   void beforeJobSchedule(AbstractJob job, AbstractTrigger trigger);

   void jobScheduled(AbstractJob job, AbstractTrigger trigger);

   void beforeJobRescheduled(AbstractJob job, AbstractTrigger trigger, AbstractJob previous);

   void beforeJobUnschedule(AbstractJob job);

   void jobRescheduled(AbstractJob job, AbstractTrigger trigger);

   void jobUnscheduled(AbstractJob job);

   void beforeJobRemove(AbstractJob job);

   void jobRemoved(AbstractJob job);

}
