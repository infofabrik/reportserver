package net.datenwerke.rs.adminutils.client.datasourcetester;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

public class ConnectionTestFailedException extends ServerCallFailedException {

   /**
    * 
    */
   private static final long serialVersionUID = 7944828583793017460L;

   public ConnectionTestFailedException() {
      super("Failed to test datasink");
   }

   public ConnectionTestFailedException(String msg) {
      super(msg);
   }

   public ConnectionTestFailedException(String msg, Exception e) {
      super(msg, e);
   }
}
