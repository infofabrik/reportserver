package net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions;

import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;

public class ObjectResolverException extends TerminalException {

   /**
    * 
    */
   private static final long serialVersionUID = 3333418348907883991L;

   public ObjectResolverException() {
      super();
   }

   public ObjectResolverException(String msg) {
      super(msg);
   }

   public ObjectResolverException(Throwable e) {
      super(e);
   }

   public ObjectResolverException(String msg, Throwable e) {
      super(msg, e);
   }
}
