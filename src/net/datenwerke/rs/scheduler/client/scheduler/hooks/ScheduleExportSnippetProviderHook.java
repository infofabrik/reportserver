package net.datenwerke.rs.scheduler.client.scheduler.hooks;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;

public interface ScheduleExportSnippetProviderHook extends Hook {

	void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs);

	boolean isValid(SimpleForm simpleForm);

	void configureConfig(ReportScheduleDefinition configDto,
			SimpleForm simpleForm);

	boolean isActive(SimpleForm simpleForm);

	void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report);
	
	void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition, ReportDto report);

	boolean appliesFor(ReportDto report,
			Collection<ReportViewConfiguration> configs);
	
}
