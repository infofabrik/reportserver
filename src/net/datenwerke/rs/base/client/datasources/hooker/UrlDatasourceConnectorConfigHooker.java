package net.datenwerke.rs.base.client.datasources.hooker;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.UrlDatasourceConnectorDtoPA;
import net.datenwerke.rs.base.client.datasources.hooks.DatasourceConnectorConfiguratorHook;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;

public class UrlDatasourceConnectorConfigHooker implements
		DatasourceConnectorConfiguratorHook {

	@Override
	public Widget configureForm(FormatBasedDatasourceDefinitionDto datasource) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		form.addField(String.class, UrlDatasourceConnectorDtoPA.INSTANCE.url(), BaseDatasourceMessages.INSTANCE.urlLabel());
		
		form.bind(datasource.getConnector());
		
		return form;
	}

	@Override
	public DatasourceConnectorDto instantiateConnector() {
		return new UrlDatasourceConnectorDto();
	}

	@Override
	public String getConnectorName() {
		return BaseDatasourceMessages.INSTANCE.urlConnectorName();
	}

	@Override
	public boolean consumes(DatasourceConnectorDto connector) {
		return connector instanceof UrlDatasourceConnectorDto;
	}

}
