package net.datenwerke.rs.terminal.service.terminal.vfs.exceptions;

import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;

public class LocationDoesNotExistException extends VFSException {

   /**
    * 
    */
   private static final long serialVersionUID = -7285962205382472675L;

   public LocationDoesNotExistException(String msg) {
      super(msg);
   }

   public LocationDoesNotExistException() {
      super(TerminalMessages.INSTANCE.locationDoesNotExistException());
   }

}
