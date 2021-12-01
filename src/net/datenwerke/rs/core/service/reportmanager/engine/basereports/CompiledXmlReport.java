package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

public class CompiledXmlReport extends CompiledTextReportImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3923172282546238585L;

	public CompiledXmlReport(String report) {
		super(report);
	}

	@Override
	public String getMimeType() {
		return "application/xml";
	}

	@Override
	public String getFileExtension() {
		return "xml";
	}

}
