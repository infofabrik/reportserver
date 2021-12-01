package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledXLSXTableReport extends CompiledTableReport implements CompiledReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3142311923457795982L;
	
	final private byte[] report;
	
	public CompiledXLSXTableReport(byte[] report) {
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
}
