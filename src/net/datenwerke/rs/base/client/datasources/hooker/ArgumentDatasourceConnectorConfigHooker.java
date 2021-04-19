package net.datenwerke.rs.base.client.datasources.hooker;

import net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.decorator.ArgumentDatasourceConnectorDtoDec;
import net.datenwerke.rs.base.client.datasources.hooks.DatasourceConnectorConfiguratorHook;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;

import com.google.gwt.user.client.ui.Widget;

public class ArgumentDatasourceConnectorConfigHooker implements
		DatasourceConnectorConfiguratorHook {

	@Override
	public Widget configureForm(FormatBasedDatasourceDefinitionDto datasource) {
		return null;
	}

	@Override
	public DatasourceConnectorDto instantiateConnector() {
		return new ArgumentDatasourceConnectorDtoDec();
	}

	@Override
	public String getConnectorName() {
		return BaseDatasourceMessages.INSTANCE.argumentConnector();
	}

	@Override
	public boolean consumes(DatasourceConnectorDto connector) {
		return connector instanceof ArgumentDatasourceConnectorDto;
	}

}
