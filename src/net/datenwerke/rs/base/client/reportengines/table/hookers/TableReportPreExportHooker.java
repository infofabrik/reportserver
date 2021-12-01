package net.datenwerke.rs.base.client.reportengines.table.hookers;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterPreExportHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class TableReportPreExportHooker implements ReportExporterPreExportHook {

	@Override
	public String isExportable(ReportDto report) {
		if(report instanceof TableReportDto && !((TableReportDto)report).isCubeFlag()){
			if(((TableReportDto) report).getColumns().isEmpty()){
				return ReportexecutorMessages.INSTANCE.noColumnsSelected();
			}
		}
		return null;
	}

}
