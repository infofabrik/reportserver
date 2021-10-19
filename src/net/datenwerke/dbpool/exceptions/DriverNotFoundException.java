package net.datenwerke.dbpool.exceptions;

public class DriverNotFoundException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 7332179643588043194L;

   public DriverNotFoundException(String driver, ClassNotFoundException e) {
      super("Driver not found: " + driver, e);
   }
}
