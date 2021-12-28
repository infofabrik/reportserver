package net.datenwerke.scheduler.service.scheduler.exceptions;

public class ActionExecutionException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 2052438444855407019L;

   public ActionExecutionException(String msg) {
      super(msg);
   }

   public ActionExecutionException(Throwable e) {
      super(e);
   }

   public ActionExecutionException(String msg, Throwable e) {
      super(msg, e);
   }
}
