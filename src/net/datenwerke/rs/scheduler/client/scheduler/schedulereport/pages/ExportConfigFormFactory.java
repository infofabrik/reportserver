package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Collection;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface ExportConfigFormFactory {

	public SchedulerExportConfigurationForm create(ReportDto report, Collection<ReportViewConfiguration> configs, ArrayList<SendToClientConfig> sendToConfigs, ReportScheduleDefinition definition);
	
}
