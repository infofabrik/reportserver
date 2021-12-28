package net.datenwerke.security.service.authenticator.exceptions;

public class AuthenticatorRuntimeException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = -2254153379814412033L;

   public AuthenticatorRuntimeException() {
      super();
   }

   public AuthenticatorRuntimeException(String msg) {
      super(msg);
   }
}
