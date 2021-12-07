package net.datenwerke.rs.ldap.service.ldap.exceptions;

public class LdapException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = -4933340586313809970L;

   public LdapException() {
      super();
   }

   public LdapException(String message, Throwable cause) {
      super(message, cause);
   }

   public LdapException(String message) {
      super(message);
   }

   public LdapException(Throwable cause) {
      super(cause);
   }

}
