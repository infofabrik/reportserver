package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

/**
 * 
 *
 */
public class CompiledJSONTableReport extends CompiledTableReport implements CompiledReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4255107863955245397L;
	
	final private String report;
	
	public CompiledJSONTableReport(String report) {
		this.report = report;
	}

	public String getReport() {
		return report;
	}

	public String getFileExtension() {
		return "json"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/json"; //$NON-NLS-1$
	}
	
}
