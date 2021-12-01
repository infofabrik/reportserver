package net.datenwerke.rs.core.service.reportmanager.engine;

public class CompiledReportImpl implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = 1427546313375018829L;

   private Object report;
   private String mimeType;
   private String fileExtension;
   private boolean data;
   private boolean stringReport;

   public CompiledReportImpl(Object report, String mimeType, String fileExtension, boolean data, boolean stringReport) {
      this.report = report;
      this.mimeType = mimeType;
      this.fileExtension = fileExtension;
      this.data = data;
      this.stringReport = stringReport;
   }

   @Override
   public Object getReport() {
      return report;
   }

   @Override
   public String getMimeType() {
      return mimeType;
   }

   @Override
   public String getFileExtension() {
      return fileExtension;
   }

   @Override
   public boolean hasData() {
      return data;
   }

   @Override
   public boolean isStringReport() {
      return stringReport;
   }

   public void setHasData(boolean hasData) {
      this.data = hasData;
   }

}
