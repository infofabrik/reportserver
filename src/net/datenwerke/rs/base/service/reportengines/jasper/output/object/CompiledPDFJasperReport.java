package net.datenwerke.rs.base.service.reportengines.jasper.output.object;

/**
 * 
 *
 */
public class CompiledPDFJasperReport extends CompiledRSJasperReport {

   /**
    * 
    */
   private static final long serialVersionUID = 8652010282977094940L;

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
      return "pdf"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/pdf"; //$NON-NLS-1$
   }

   @Override
   public boolean isStringReport() {
      return false;
   }

}
