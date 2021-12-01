package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledCSVTableReport extends CompiledTableReport implements CompiledReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446428600997886807L;
	
	final private String report;
	
	public CompiledCSVTableReport(String report) {
		this.report = report;
	}

	public String getReport() {
		return report;
	}

	public String getFileExtension() {
		return "csv"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "text/csv"; //$NON-NLS-1$
	}
	
}
