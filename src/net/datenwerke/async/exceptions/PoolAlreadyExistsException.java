package net.datenwerke.async.exceptions;

public class PoolAlreadyExistsException extends RuntimeException {

   public PoolAlreadyExistsException(String poolToken) {
      super("Pool " + poolToken + " already exists.");
   }

   /**
    * 
    */
   private static final long serialVersionUID = -6055956432254485986L;

}
