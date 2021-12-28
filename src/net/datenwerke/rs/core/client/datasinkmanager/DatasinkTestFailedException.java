package net.datenwerke.rs.core.client.datasinkmanager;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

public class DatasinkTestFailedException extends ServerCallFailedException {

   /**
    * 
    */
   private static final long serialVersionUID = 7944828583793017460L;

   public DatasinkTestFailedException() {
      super("Failed to connect");
   }

   public DatasinkTestFailedException(String msg) {
      super(msg);
   }

   public DatasinkTestFailedException(String msg, Exception e) {
      super(msg, e);
   }
}
