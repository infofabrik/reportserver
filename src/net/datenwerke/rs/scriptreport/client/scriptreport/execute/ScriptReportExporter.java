package net.datenwerke.rs.scriptreport.client.scriptreport.execute;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utils.IconGuesserService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterProviderHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;

public class ScriptReportExporter implements ReportExporterProviderHook {

	private final IconGuesserService iconGuesser;
	
	@Inject
	public ScriptReportExporter(IconGuesserService iconGuesser) {
		this.iconGuesser = iconGuesser;
	}


	@Override
	public List<ReportExporter> getExporters(ReportDto report) {
		if(! (report instanceof ScriptReportDto))
			return new ArrayList<ReportExporter>();
		
		List<ReportExporter> exporters = new ArrayList<ReportExporter>();
		
		ScriptReportDto sReport = (ScriptReportDto) report;
		for(final String format : sReport.getExportFormats()){
			exporters.add(new ReportExporterImpl() {
				
				@Override
				public boolean isConfigured() {
					return true;
				}
				
				@Override
				public boolean hasConfiguration() {
					return true;
				}
				
				@Override
				public String getOutputFormat() {
					return format;
				}
				
				@Override
				public ImageResource getIcon() {
					return iconGuesser.guessIcon(format);
				}
				
				@Override
				public String getExportTitle() {
					return format;
				}
				
				@Override
				public String getExportDescription() {
					return "";
				}
				
				@Override
				public void displayConfiguration(ReportDto report, String executorToken,
						boolean allowAutomaticConfig, ConfigurationFinishedCallback callack) {
					callack.success();
				}
				
				@Override
				public boolean consumesConfiguration(ReportDto report) {
					return report instanceof ScriptReportDto;
				}
				
				@Override
				public boolean consumes(ReportDto report) {
					return report instanceof ScriptReportDto;
				}
				
			});
		}
		
		return exporters;
	}

	

}
