package net.datenwerke.rs.keyutils.service.keyutils;

public enum MappingPrio {
   MAPPING("mapping"), SAME_KEY("same-key");
   
   private String prio;
   
   MappingPrio(String prio) {
      this.prio = prio;
   }
   
   public String getPrio() {
      return prio;
   }
}
