package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

public class RetryActionExecution implements VetoActionExecution {

   private final String explanation;
   private RetryTimeUnit unit = null;
   private int amount = 0;

   public RetryActionExecution(String explanation) {
      super();
      this.explanation = explanation;
   }

   public RetryActionExecution(String explanation, RetryTimeUnit unit, int amount) {
      super();
      this.explanation = explanation;
      this.unit = unit;
      this.amount = amount;
   }

   @Override
   public VetoActionExecutionMode getMode() {
      return VetoActionExecutionMode.RETRY;
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