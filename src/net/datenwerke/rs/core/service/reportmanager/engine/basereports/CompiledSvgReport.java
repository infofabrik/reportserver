package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

public class CompiledSvgReport extends CompiledTextReportImpl {

   private static final long serialVersionUID = 1747664505551353448L;

   public CompiledSvgReport(String report) {
      super(report);
   }

   @Override
   public String getMimeType() {
      return "image/svg+xml";
   }

   @Override
   public String getFileExtension() {
      return "svg";
   }

}
