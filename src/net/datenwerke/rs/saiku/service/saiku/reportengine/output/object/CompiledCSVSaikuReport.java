package net.datenwerke.rs.saiku.service.saiku.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledCsvReport;

public class CompiledCSVSaikuReport extends CompiledCsvReport implements CompiledRSSaikuReport {

	private static final long serialVersionUID = 2229630261005776059L;

	public CompiledCSVSaikuReport(){
		super(null);
	}
	
	public CompiledCSVSaikuReport(String report) {
		super(report);
	}

	
}
