package net.datenwerke.rs.base.service.reportengines.jasper.output.object;

/**
 * 
 *
 */
public class CompiledRTFJasperReport extends CompiledRSJasperReport {

   /**
    * 
    */
   private static final long serialVersionUID = 317607825564843386L;

   private String report;

   public String getReport() {
      return report;
   }

   public void setReport(Object report) {
      try {
         this.report = (String) report;
      } catch (ClassCastException e) {
         IllegalArgumentException iae = new IllegalArgumentException("Expected String"); //$NON-NLS-1$
         iae.initCause(e);
         throw iae;
      }
   }

   public String getFileExtension() {
      return "rtf"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "application/rtf"; //$NON-NLS-1$
   }

}
