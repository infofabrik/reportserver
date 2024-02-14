package net.datenwerke.rs.core.client.transport;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public class TransportApplyFailedException extends ExpectedException {

   /**
    * 
    */
   private static final long serialVersionUID = 7944828583793017460L;

   public TransportApplyFailedException() {
      super("Failed to apply Transport");
   }

   public TransportApplyFailedException(String msg) {
      super(msg);
   }

   public TransportApplyFailedException(String msg, Exception e) {
      super(msg, e);
   }
}
