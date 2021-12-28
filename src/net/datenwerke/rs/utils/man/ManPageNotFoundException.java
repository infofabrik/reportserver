package net.datenwerke.rs.utils.man;

public class ManPageNotFoundException extends IllegalArgumentException {

   public ManPageNotFoundException() {
      super();
   }

   public ManPageNotFoundException(String msg, Throwable t) {
      super(msg, t);
   }

   public ManPageNotFoundException(String msg) {
      super(msg);
   }

   public ManPageNotFoundException(Throwable msg) {
      super(msg);
   }

}
