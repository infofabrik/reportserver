package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledPdfReport;

public class CompiledPDFSaikuReport extends CompiledPdfReport implements CompiledRSSaikuReport {

	private static final long serialVersionUID = -3212117079422754975L;

	public CompiledPDFSaikuReport() {
		super(null);
	}
	
	public CompiledPDFSaikuReport(Object report) {
		super(report);
	}

}
