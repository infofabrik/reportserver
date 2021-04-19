package net.datenwerke.rs.dashboard.client.dashboard.hooks;

import java.util.Set;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;

public interface ReportDadgetExportHook extends Hook {

	boolean consumes(ReportDadgetDto dadget);

	void configureDisplayConfigDialog(ReportDadgetDto dadget, SimpleForm form);

	void storeConfig(ReportDadgetDto dadget, SimpleForm form);

	void displayReport(ReportDadgetDto rDadget, ReportDto report,
			DadgetPanel panel, Set<ParameterInstanceDto> parameterInstances);

	String getPropertyName();

}
