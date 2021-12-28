package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECMetadata implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = -651679719893590000L;

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECMetadata)
         return true;
      else
         return false;
   }
}
