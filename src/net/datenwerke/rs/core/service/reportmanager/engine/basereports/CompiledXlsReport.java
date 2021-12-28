package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledXlsReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = 818157248038527553L;

   final private byte[] report;

   public CompiledXlsReport(byte[] report) {
      this.report = report;
   }

   public byte[] getReport() {
      return report;
   }

   public String getFileExtension() {
      return "xls"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/excel"; //$NON-NLS-1$
   }

   @Override
   public boolean isStringReport() {
      return false;
   }

   @Override
   public boolean hasData() {
      return report != null;
   }
}
