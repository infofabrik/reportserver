package net.datenwerke.rs.crystal.client.crystal.hookers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;

public class CrystalReportDadgetExporter extends ReportDadgetDefaultExportHooker {

	private static final String PNG = "PNG";

	@Inject
	public CrystalReportDadgetExporter(
			ReportExecutorUIService reportExecutorService,
			ReportExporterUIService reportExportService,
			ReportExecutorDao reportExecutorDao,
			ReportExporterDao reportExporterDao) {
		super(reportExecutorService, reportExportService, reportExecutorDao, reportExporterDao);
		
	}
	
	@Override
	protected Map<String, ImageResource> getIconMap() {
		LinkedHashMap<String, ImageResource> icons = new LinkedHashMap<String, ImageResource>();
		
		icons.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), BaseIcon.REPORT_PICTURE.toImageResource(1));
		icons.put(DashboardMessages.INSTANCE.reportDadgetFormatImage(), BaseIcon.WEBPAGE.toImageResource(1));
		icons.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), BaseIcon.EYE.toImageResource(1));
			
		return icons;
	}

	@Override
	protected Map<String, String> getValueMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		map.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), PREVIEW);
		map.put(DashboardMessages.INSTANCE.reportDadgetFormatImage(), PNG);
		map.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), FULL);
		
		return map;
	}

	@Override
	public String getPropertyName() {
		return "crystalConfig";
	}

	@Override
	protected boolean isSupportedReport(ReportDto report) {
		return report instanceof CrystalReportDto;
	}

}
