package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledDocReport implements CompiledReport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7935299997822053401L;
	
	final private byte[] report;
	
	public CompiledDocReport(byte[] report) {
		this.report = report;
	}

	public byte[] getReport() {
		return report;
	}

	public String getFileExtension() {
		return "doc"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/msword"; //$NON-NLS-1$
	}

	@Override
	public boolean isStringReport() {
		return false;
	}

	@Override
	public boolean hasData() {
		return report != null;
	}
}
