package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledDocxTableReport extends CompiledTableReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = -446996212992231617L;
   final private byte[] report;

   public CompiledDocxTableReport(byte[] report) {
      this.report = report;
   }

   public byte[] getReport() {
      return report;
   }

   public String getFileExtension() {
      return "docx"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/vnd.openxmlformats-officedocument.wordprocessingml.document"; //$NON-NLS-1$
   }

   @Override
   public boolean isStringReport() {
      return false;
   }
}
