package net.datenwerke.rs.base.service.reportengines.jasper.output.object;

/**
 * 
 *
 */
public class CompiledXLSXJasperReport extends CompiledRSJasperReport {

   /**
    * 
    */
   private static final long serialVersionUID = 1235594273026554741L;

   private byte[] report;

   public byte[] getReport() {
      return report;
   }

   public void setReport(Object report) {
      try {
         this.report = (byte[]) report;
      } catch (ClassCastException e) {
         IllegalArgumentException iae = new IllegalArgumentException("Expected byte array"); //$NON-NLS-1$
         iae.initCause(e);
         throw iae;
      }
   }

   public String getFileExtension() {
      return "xlsx"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; //$NON-NLS-1$
   }

}
