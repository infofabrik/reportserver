package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledDocxReport implements CompiledReport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -908845164270111862L;
	
	/**
	 * 
	 */
	final private byte[] report;
	
	public CompiledDocxReport(byte[] report) {
		this.report = report;
	}

	public byte[] getReport() {
		return report;
	}

	public String getFileExtension() {
		return "docx"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/vnd.openxmlformats-officedocument.wordprocessingml.document"; //$NON-NLS-1$
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
