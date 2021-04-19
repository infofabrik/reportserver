package net.datenwerke.rs.scriptreport.client.scriptreport.hookers;

import javax.inject.Inject;

import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;

public class ScriptReportDadgetExporter extends ReportDadgetDefaultExportHooker {

	@Inject
	public ScriptReportDadgetExporter(ReportExecutorUIService reportExecutorService,
			ReportExporterUIService reportExportService,
			ReportExecutorDao reportExecutorDao,
			ReportExporterDao reportExporterDao) {
		super(reportExecutorService, reportExportService, reportExecutorDao, reportExporterDao);
	}

	@Override
	protected boolean isSupportedReport(ReportDto report) {
		return report instanceof ScriptReportDto;
	}

	@Override
	public String getPropertyName() {
		return "scriptConfig";
	}
}
