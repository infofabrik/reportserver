package net.datenwerke.rs.eximport.service.eximport.im.http;

import java.io.Serializable;

import net.datenwerke.eximport.ExportDataProvider;
import net.datenwerke.eximport.im.ImportConfig;

public class HttpImportConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043340618318521368L;

	private ExportDataProvider importData;
	private ImportConfig importConfig;
	
	
	public ExportDataProvider getImportData() {
		return importData;
	}

	public void setImportData(ExportDataProvider importData) {
		this.importData = importData;
	}

	public void setImportConfig(ImportConfig importConfig) {
		this.importConfig = importConfig;
	}

	public ImportConfig getImportConfig() {
		return importConfig;
	}




}
