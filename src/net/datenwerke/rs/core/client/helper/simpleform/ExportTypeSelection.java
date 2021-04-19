package net.datenwerke.rs.core.client.helper.simpleform;

import java.util.List;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;

public class ExportTypeSelection {

	private String outputFormat;
	private List<ReportExecutionConfigDto> exportConfiguration;
	private boolean configured = false;
	
	public String getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	
	public List<ReportExecutionConfigDto> getExportConfiguration() {
		return exportConfiguration;
	}
	public void setExportConfiguration(
			List<ReportExecutionConfigDto> exportConfiguration) {
		this.exportConfiguration = exportConfiguration;
	}
	
	public void setConfigured(boolean configured) {
		this.configured = configured;
	}
	public boolean isConfigured() {
		return configured;
	}
	
	
}
