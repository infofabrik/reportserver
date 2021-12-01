package net.datenwerke.rs.base.client.datasources.hooker;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.TextDatasourceConnectorDtoPA;
import net.datenwerke.rs.base.client.datasources.hooks.DatasourceConnectorConfiguratorHook;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;

import com.google.gwt.user.client.ui.Widget;

public class TextDatasourceConnectorConfigHooker implements
		DatasourceConnectorConfiguratorHook {

	@Override
	public Widget configureForm(FormatBasedDatasourceDefinitionDto datasource) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		form.addField(String.class, TextDatasourceConnectorDtoPA.INSTANCE.data(), BaseDatasourceMessages.INSTANCE.dataLabel(), new SFFCTextArea(){ 

			public int getHeight() {
				return 300;
			}

			public int getWidth() {
				return 400;
			}
			
		});
		
		form.bind(datasource.getConnector());
		
		return form;
	}

	@Override
	public DatasourceConnectorDto instantiateConnector() {
		return new TextDatasourceConnectorDto();
	}

	@Override
	public String getConnectorName() {
		return BaseDatasourceMessages.INSTANCE.textConnectorName();
	}

	@Override
	public boolean consumes(DatasourceConnectorDto connector) {
		return connector instanceof TextDatasourceConnectorDto;
	}

}
