package net.datenwerke.rs.base.service.reportengines.table.dot.http;

public interface HttpExportService {

   void storeExport(String exportDot, String name);

   String getAndRemoveStoredExport();

   String getExportName();

}
