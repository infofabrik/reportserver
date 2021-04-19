package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface SeriesConfigFormFactory {

	public SeriesConfigurationForm create(ReportDto report, ReportScheduleDefinition definition);
	
}
