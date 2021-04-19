package net.datenwerke.rs.scheduleasfile.server.scheduleasfile.events;

import java.util.List;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.utils.eventbus.Event;

public class ExportReportIntoTeamSpaceFailedEvent implements Event {

	private ReportDto reportDto;
	private String executorToken;
	private String format;
	private List<ReportExecutionConfigDto> configs;
	private AbstractTsDiskNodeDto folder;
	private String name;
	private String description;
	
	public ExportReportIntoTeamSpaceFailedEvent(){}
	
	public ExportReportIntoTeamSpaceFailedEvent(ReportDto reportDto,
			String executorToken, String format,
			List<ReportExecutionConfigDto> configs, AbstractTsDiskNodeDto folder,
			String name, String description) {
		this.reportDto = reportDto;
		this.executorToken = executorToken;
		this.format = format;
		this.configs = configs;
		this.folder = folder;
		this.name = name;
		this.description = description;
	}
	
	public ReportDto getReportDto() {
		return reportDto;
	}
	public String getExecutorToken() {
		return executorToken;
	}
	public String getFormat() {
		return format;
	}
	public List<ReportExecutionConfigDto> getConfigs() {
		return configs;
	}
	public AbstractTsDiskNodeDto getFolder() {
		return folder;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	
}
