package net.datenwerke.rs.base.ext.service;

public enum RemoteEntityImportPrio {
   MAPPING("mapping"), SAME_NAME("same-name");
   
   private String prio;
   
   RemoteEntityImportPrio(String prio) {
      this.prio = prio;
   }
   
   public String getPrio() {
      return prio;
   }
}
