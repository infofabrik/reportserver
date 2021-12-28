package net.datenwerke.rs.scheduler.service.scheduler.exceptions;

public class SchedulerRuntimeException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = -4280647439154893477L;

   public SchedulerRuntimeException() {
      super();
   }

   public SchedulerRuntimeException(String msg) {
      super(msg);
   }
}
