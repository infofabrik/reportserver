package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public abstract class CompiledJxlsReport implements CompiledReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1244531738132655709L;

	final private byte[] report;
	
	public CompiledJxlsReport(byte[] report) {
		this.report = report;
	}

	public byte[] getReport() {
		return report;
	}
	
	@Override
	public boolean isStringReport() {
		return false;
	}
	
	@Override
	public boolean hasData() {
		return null != report && report.length > 0;
	}
}
