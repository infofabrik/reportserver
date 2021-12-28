package net.datenwerke.rs.core.service.reportmanager.engine.config;

import java.util.Objects;

public class RECSetExecutionUUID implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = -1862083712620846442L;

   private String uuid;

   public RECSetExecutionUUID(String uuid) {
      this.uuid = uuid;
   }

   public String getUuid() {
      return uuid;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(uuid);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECSetExecutionUUID) {
         final RECSetExecutionUUID other = (RECSetExecutionUUID) obj;
         return Objects.equals(uuid, other.uuid);
      } else {
         return false;
      }
   }

}
