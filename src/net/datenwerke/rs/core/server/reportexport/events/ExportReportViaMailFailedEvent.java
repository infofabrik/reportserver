package net.datenwerke.rs.core.server.reportexport.events;

import java.util.List;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.utils.eventbus.Event;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ExportReportViaMailFailedEvent implements Event {

	
	private ReportDto reportDto;
	private String executorToken; 
	private String format;
	private List<ReportExecutionConfigDto> configs; 
	private String subject;
	private String message ;
	private List<StrippedDownUser> recipients;
	
	public ExportReportViaMailFailedEvent(){
		
	}

	public ExportReportViaMailFailedEvent(ReportDto reportDto,
			String executorToken, String format,
			List<ReportExecutionConfigDto> configs, String subject,
			String message, List<StrippedDownUser> recipients) {
		super();
		this.reportDto = reportDto;
		this.executorToken = executorToken;
		this.format = format;
		this.configs = configs;
		this.subject = subject;
		this.message = message;
		this.recipients = recipients;
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

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public List<StrippedDownUser> getRecipients() {
		return recipients;
	}
	
	

}
