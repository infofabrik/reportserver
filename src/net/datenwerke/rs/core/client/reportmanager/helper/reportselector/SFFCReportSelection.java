package net.datenwerke.rs.core.client.reportmanager.helper.reportselector;

import java.util.Collection;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCReportSelection extends SimpleFormFieldConfiguration {

	public Collection<ReportSelectionDialog.RepositoryProviderConfig> getRepositoryConfigs();
	
	public boolean showCatalog();
	public boolean showVariantsInCatalog();
}
