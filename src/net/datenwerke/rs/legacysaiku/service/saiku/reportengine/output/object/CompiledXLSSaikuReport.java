package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledXlsxReport;

public class CompiledXLSSaikuReport extends CompiledXlsxReport implements CompiledRSSaikuReport {

	private static final long serialVersionUID = 1684884393282821852L;

	public CompiledXLSSaikuReport() {
		super(null);
	}
	
	public CompiledXLSSaikuReport(byte[] report) {
		super(report);
	}

}
