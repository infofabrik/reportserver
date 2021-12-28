package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object;

/**
 * 
 *
 */
public class CompiledJxlsXlsReport extends CompiledJxlsReport {

   /**
    * 
    */
   private static final long serialVersionUID = 7968434291722854647L;

   public CompiledJxlsXlsReport(byte[] report) {
      super(report);
   }

   @Override
   public String getFileExtension() {
      return "xls"; //$NON-NLS-1$
   }

   @Override
   public String getMimeType() {
      return "application/excel"; //$NON-NLS-1$
   }

}
