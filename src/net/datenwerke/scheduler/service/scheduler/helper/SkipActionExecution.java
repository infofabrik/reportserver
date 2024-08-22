package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

public class SkipActionExecution implements VetoActionExecution {

   private final String explanation;

   public SkipActionExecution(String explanation) {
      super();
      this.explanation = explanation;
   }

   @Override
   public VetoActionExecutionMode getMode() {
      return VetoActionExecutionMode.SKIP;
   }

   @Override
   public String getExplanation() {
      return explanation;
   }

   @Override
   public void updateTrigger(AbstractJob job, ExecutionLogEntry logEntry) {

   }

   @Override
   public RetryTimeUnit getRetryUnit() {
      return null;
   }

   @Override
   public int getRetryAmount() {
      return 0;
   }

}