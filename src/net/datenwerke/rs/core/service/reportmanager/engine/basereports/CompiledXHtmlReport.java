package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

public class CompiledXHtmlReport extends CompiledHtmlReportImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5294630271909422996L;

	public CompiledXHtmlReport(String report) {
		super(report);
	}
	
	@Override
	public String getMimeType() {
		return "application/xhtml+xml";
	}

}
