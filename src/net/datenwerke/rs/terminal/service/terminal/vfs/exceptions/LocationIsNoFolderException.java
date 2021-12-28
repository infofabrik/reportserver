package net.datenwerke.rs.terminal.service.terminal.vfs.exceptions;

import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;

public class LocationIsNoFolderException extends VFSException {

   /**
    * 
    */
   private static final long serialVersionUID = 5265807977501109310L;

   public LocationIsNoFolderException(String msg) {
      super(msg);
   }

   public LocationIsNoFolderException() {
      super(TerminalMessages.INSTANCE.locationIsNoFolderException());
   }

}
