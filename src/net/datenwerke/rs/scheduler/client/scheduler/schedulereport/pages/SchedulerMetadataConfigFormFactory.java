package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface SchedulerMetadataConfigFormFactory {

	public JobMetadataConfigurationForm create(ReportScheduleDefinition definition);
	
}
