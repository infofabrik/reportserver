package net.datenwerke.rs.terminal.service.terminal.vfs.exceptions;

public class VFSException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 4268082851696829145L;

   public VFSException(String msg) {
      super(msg);
   }

   public VFSException() {
      super();
   }

   public VFSException(Throwable e) {
      super(e);
   }

}
