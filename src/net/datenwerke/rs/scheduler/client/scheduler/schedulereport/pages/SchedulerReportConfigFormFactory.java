package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleConfigWizardPageProviderHook;

public interface SchedulerReportConfigFormFactory {

   public JobReportConfigurationForm create(Optional<ReportDto> report, Collection<ReportViewConfiguration> configs,
         ReportScheduleDefinition definition, List<ScheduleConfigWizardPageProviderHook> advancedPages);

}
