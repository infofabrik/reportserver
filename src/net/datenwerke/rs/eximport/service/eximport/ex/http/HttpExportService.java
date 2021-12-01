package net.datenwerke.rs.eximport.service.eximport.ex.http;

public interface HttpExportService {

	void storeExport(String exportXML, String name);

	String getAndRemoveStoredExport();

	String getExportName();

}
