package net.datenwerke.scheduler.service.scheduler.exceptions;

public class JobExecutionException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 4799005861190484816L;

   public JobExecutionException(String msg) {
      super(msg);
   }

   public JobExecutionException(Throwable e) {
      super(e);
   }
}
