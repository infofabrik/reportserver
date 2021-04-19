package net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigExecution;

public interface SFFCDatasourceSpecificConfig extends SimpleFormFieldConfiguration {

	public DatabaseSpecificFieldConfigExecution getConfigExecution();
}