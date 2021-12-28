package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledJsonReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = 485512409594043368L;

   final private String report;

   public CompiledJsonReport(String report) {
      this.report = report;
   }

   public String getReport() {
      return report;
   }

   public String getFileExtension() {
      return "json"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/json"; //$NON-NLS-1$
   }

   @Override
   public boolean hasData() {
      return report != null;
   }

   @Override
   public boolean isStringReport() {
      return true;
   }

}
