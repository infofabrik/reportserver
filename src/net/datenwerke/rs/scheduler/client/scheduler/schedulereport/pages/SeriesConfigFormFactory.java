package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface SeriesConfigFormFactory {

	public SeriesConfigurationForm create(ReportScheduleDefinition definition);
	
}
