package net.datenwerke.rs.eximport.service.eximport.ex.http;

public class HttpExportServiceImpl implements HttpExportService {

	private String storedExport;
	private String name;
	
	@Override
	public void storeExport(String exportXML, String name) {
		this.storedExport = exportXML;
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
