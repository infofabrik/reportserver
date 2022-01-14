package net.datenwerke.gxtdto.client.servercommunication.exceptions;

public class NeedForcefulDeleteClientException extends ExpectedException {

   /**
    * 
    */
   private static final long serialVersionUID = -7247330086204348976L;

   public NeedForcefulDeleteClientException() {
      super();
   }

   public NeedForcefulDeleteClientException(String msg) {
      super(msg);
   }

   public NeedForcefulDeleteClientException(Throwable t) {
      super(t);
   }
}
