package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledCsvReport implements CompiledReport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3429904471397233389L;
	final private String report;
	
	public CompiledCsvReport(String report) {
		this.report = report;
	}

	@Override
	public String getReport() {
		return report;
	}

	@Override
	public String getFileExtension() {
		return "csv"; //$NON-NLS-1$
	}

	@Override
	public String getMimeType() {
		return "text/csv"; //$NON-NLS-1$
	}

	@Override
	public boolean hasData() {
		return report != null;
	}

	@Override
	public boolean isStringReport() {
		return true;
	}
	
}
