package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECReportInformation implements ReportExecutionConfig {

   private static final long serialVersionUID = -651679719893590000L;

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECReportInformation)
         return true;
      else
         return false;
   }
}
