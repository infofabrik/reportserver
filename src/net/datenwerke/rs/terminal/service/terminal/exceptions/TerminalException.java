package net.datenwerke.rs.terminal.service.terminal.exceptions;

public class TerminalException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 1626747524465898660L;

   public TerminalException() {
      super();
   }

   public TerminalException(String msg) {
      super(msg);
   }

   public TerminalException(Throwable e) {
      super(e);
   }

   public TerminalException(String msg, Throwable e) {
      super(msg, e);
   }
}
