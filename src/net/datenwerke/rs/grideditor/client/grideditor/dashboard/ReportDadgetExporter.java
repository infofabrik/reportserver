package net.datenwerke.rs.grideditor.client.grideditor.dashboard;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

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
		return report instanceof GridEditorReportDto;
	}
	
	@Override
	protected Map<String, ImageResource> getIconMap() {
		LinkedHashMap<String, ImageResource> icons = new LinkedHashMap<String, ImageResource>();
		
		icons.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), BaseIcon.REPORT_PICTURE.toImageResource(1));
		icons.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), BaseIcon.EYE.toImageResource(1));
			
		return icons;
	}

	@Override
	protected Map<String, String> getValueMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		map.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), PREVIEW);
		map.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), FULL);
		
		return map;
	}


	@Override
	public String getPropertyName() {
		return "gridEditorConfig";
	}
}
