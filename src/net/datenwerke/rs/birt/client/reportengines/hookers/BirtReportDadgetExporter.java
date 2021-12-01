package net.datenwerke.rs.birt.client.reportengines.hookers;

import javax.inject.Inject;

import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;

public class BirtReportDadgetExporter extends ReportDadgetDefaultExportHooker {

	@Inject
	public BirtReportDadgetExporter(ReportExecutorUIService reportExecutorService,
			ReportExporterUIService reportExportService,
			ReportExecutorDao reportExecutorDao,
			ReportExporterDao reportExporterDao) {
		super(reportExecutorService, reportExportService, reportExecutorDao, reportExporterDao);
	}

	@Override
	protected boolean isSupportedReport(ReportDto report) {
		return report instanceof BirtReportDto;
	}
	
	@Override
	public String getPropertyName() {
		return "birtConfig";
	}

}
