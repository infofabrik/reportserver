package net.datenwerke.rs.scheduler.client.scheduler.hooks;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface ScheduleConfigWizardPageProviderHook extends Hook {

   boolean isAdvanced();

   Widget getPage(ReportDto report, ReportScheduleDefinition definition);

   void configureConfig(ReportScheduleDefinition configDto, ReportDto report);

   boolean isConfigured(ReportDto report, ReportScheduleDefinition definition);

}
