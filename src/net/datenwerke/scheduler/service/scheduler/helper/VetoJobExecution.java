package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

public interface VetoJobExecution {

   VetoJobExecutionMode getMode();

   String getExplanation();

   RetryTimeUnit getRetryUnit();

   int getRetryAmount();

   void updateTrigger(AbstractJob job, ExecutionLogEntry logEntry);
}
