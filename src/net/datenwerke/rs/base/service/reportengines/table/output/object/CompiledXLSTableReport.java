package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledXLSTableReport extends CompiledTableReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = -875662961695339486L;

   final private byte[] report;

   public CompiledXLSTableReport(byte[] report) {
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
}
