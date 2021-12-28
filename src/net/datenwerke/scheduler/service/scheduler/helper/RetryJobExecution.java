package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

public class RetryJobExecution implements VetoJobExecution {

   private final String explanation;
   private RetryTimeUnit unit = null;
   private int amount = 0;

   public RetryJobExecution(String explanation) {
      super();
      this.explanation = explanation;
   }

   public RetryJobExecution(String explanation, RetryTimeUnit unit, int amount) {
      super();
      this.explanation = explanation;
      this.unit = unit;
      this.amount = amount;
   }

   @Override
   public VetoJobExecutionMode getMode() {
      return VetoJobExecutionMode.RETRY;
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
      return unit;
   }

   @Override
   public int getRetryAmount() {
      return amount;
   }

}
