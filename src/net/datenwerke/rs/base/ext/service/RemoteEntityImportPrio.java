package net.datenwerke.rs.base.ext.service;

public enum RemoteEntityImportPrio {
   MAPPING("mapping"), SAME_KEY("same-key");
   
   private String prio;
   
   RemoteEntityImportPrio(String prio) {
      this.prio = prio;
   }
   
   public String getPrio() {
      return prio;
   }
}
