package net.datenwerke.gf.client.treedb.helper.menu;

public interface AvailabilityCallback {

   public AvailabilityCallback TRUE_INSTANCE = new AvailabilityCallback() {
      @Override
      public boolean isAvailable() {
         return true;
      }
   };

   boolean isAvailable();

}
