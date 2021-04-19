package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledXlsxReport  implements CompiledReport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1717475998016257036L;
	
	final private byte[] report;
	
	public CompiledXlsxReport(byte[] report) {
		this.report = report;
	}

	public byte[] getReport() {
		return report;
	}

	public String getFileExtension() {
		return "xlsx"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; //$NON-NLS-1$
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
