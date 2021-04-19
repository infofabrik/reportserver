package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledXLSMTableReport extends CompiledTableReport implements CompiledReport{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3201695582498172613L;
	
	final private byte[] report;
	
	public CompiledXLSMTableReport(byte[] report) {
		this.report = report;
	}

	public byte[] getReport() {
		return report;
	}

	public String getFileExtension() {
		return "xlsm"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/vnd.ms-excel.sheet.macroEnabled.12"; //$NON-NLS-1$
	}
	
	@Override
	public boolean isStringReport() {
		return false;
	}
}
