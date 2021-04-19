package net.datenwerke.rs.base.ext.client.dashboard;

import javax.inject.Inject;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;

public class ReportDadgetExporter extends ReportDadgetDefaultExportHooker {

	@Inject
	public ReportDadgetExporter(ReportExecutorUIService reportExecutorService,
			ReportExporterUIService reportExportService,
			ReportExecutorDao reportExecutorDao,
			ReportExporterDao reportExporterDao) {
		super(reportExecutorService, reportExportService, reportExecutorDao, reportExporterDao);
	}

	@Override
	protected boolean isSupportedReport(ReportDto report) {
		return report instanceof JasperReportDto || (report instanceof TableReportDto && ! ((TableReportDto)report).isCubeFlag());
	}

	@Override
	public String getPropertyName() {
		return "tabJasConfig";
	}
}
