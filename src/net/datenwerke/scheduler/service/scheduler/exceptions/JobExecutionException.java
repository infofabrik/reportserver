package net.datenwerke.scheduler.service.scheduler.exceptions;

public class JobExecutionException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 4799005861190484816L;

   private String details;
   
   public JobExecutionException(String msg) {
      super(msg);
   }
   
   public JobExecutionException(String msg, String details) {
      super(msg);
      this.details = details;
   }

   public JobExecutionException(Throwable e) {
      super(e);
   }
   
   public JobExecutionException(Throwable e, String details) {
      super(e);
      this.details = details;
   }
   
   public String getDetails() {
      return details;
   }
}
