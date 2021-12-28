package net.datenwerke.scheduler.service.scheduler.stores;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.tasks.SchedulerTask;

public interface JobExecutionCompanion {

   public AbstractJob initJobForExecution(AbstractJob job);

   public AbstractJob beginSchedulerTask(AbstractJob job);

   public void endSchedulerTask(AbstractJob job, ExecutionLogEntry logEntry, boolean badFailure);

   public void updateStateAfterJobExecution(AbstractJob job, ExecutionLogEntry entry, JobEntry jobEntry,
         boolean success);

   public ExecutionLogEntry initHistoryEntry(AbstractJob job);

   public void updateStateAfterActionExecution(AbstractJob job, ExecutionLogEntry entry, ActionEntry actionEntry,
         boolean success);

   public AbstractAction initActionForExecution(AbstractAction action);

   public void beginInnerTransaction(SchedulerTask schedulerTask);

   void finishInnerTransaction(SchedulerTask schedulerTask, boolean success);

   public void failExecutingJob(AbstractJob job);

   public void resetWaitingJob(AbstractJob job);

   public void flush();

   public ActionEntry initActionEntry(ExecutionLogEntry logEntry);

   public JobEntry initJobEntry(ExecutionLogEntry entry);

}
