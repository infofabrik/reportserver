package net.datenwerke.scheduler.service.scheduler.tasks;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

public class SchedulerTaskResult {

   private final AbstractJob job;

   public SchedulerTaskResult(AbstractJob job) {
      this.job = job;
   }

   public AbstractJob getJob() {
      return job;
   }

}
