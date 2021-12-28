package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECCountData implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = -1094644694860451863L;

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECCountData)
         return true;
      else
         return false;
   }
}
