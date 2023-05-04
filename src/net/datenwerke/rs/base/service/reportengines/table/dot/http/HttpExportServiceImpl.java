package net.datenwerke.rs.base.service.reportengines.table.dot.http;

public class HttpExportServiceImpl implements HttpExportService {

   private String storedExport;
   private String name;

   @Override
   public void storeExport(String exportDot, String name) {
      this.storedExport = exportDot;
      this.name = name;
   }

   @Override
   public String getAndRemoveStoredExport() {
      String result = storedExport;
      storedExport = null;
      return result;
   }

   @Override
   public String getExportName() {
      return name;
   }

}
