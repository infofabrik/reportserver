package net.datenwerke.rs.birt.service.reportengine.output.generator;

import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;

public interface BirtOutputGenerator extends ReportOutputGenerator {
	
	public CompiledRSBirtReport exportReport(Object runAndRenderTask, String outputFormat, ReportExecutionConfig... configs);

}
