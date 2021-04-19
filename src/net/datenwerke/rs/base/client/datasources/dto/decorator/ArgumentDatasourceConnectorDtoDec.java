package net.datenwerke.rs.base.client.datasources.dto.decorator;

import java.util.List;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.rs.base.client.datasources.config.CsvDatasourceConfigConfigurator;
import net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 * Dto Decorator for {@link ArgumentDatasourceConnectorDto}
 *
 */
public class ArgumentDatasourceConnectorDtoDec extends ArgumentDatasourceConnectorDto {

	private static final long serialVersionUID = 709218674495577623L;
	private static final String TEXT_AREA_WIDGET_DATAMAP_KEY = "TEXT_AREA_WIDGET_DATAMAP";
	private static final String CONNECTOR_CFG_KEY = "ARGUMENT_DATASRC_CNCTR_CFG";
	
	public ArgumentDatasourceConnectorDtoDec() {
		super();
	}

	@Override
	public void addConnectorSpecificFormFields(List<Widget> widgets,
			DatasourceDefinitionConfigDto config,
			DatasourceDefinitionDto datasourceDefinitionDto,
			final DatasourceSelectionField datasourceSelectionField, 
			CsvDatasourceConfigConfigurator configConfigurator) {
		

		TextArea textArea = (TextArea) SimpleForm.createFormlessField(String.class, new SFFCTextAreaImpl());
		List<DatasourceConnectorConfigDto> connectorConfig = ((FormatBasedDatasourceConfigDto) config).getConnectorConfig();
		for(DatasourceConnectorConfigDto ccfg : connectorConfig){
			if(CONNECTOR_CFG_KEY.equals(ccfg.getKey())){
				textArea.setValue(ccfg.getValue());
			}
		}
		
		((HasValueChangeHandlers<String>)textArea).addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				datasourceSelectionField.updateDatasourceConfig();
			}
		});

		configConfigurator.getDatamap().put(TEXT_AREA_WIDGET_DATAMAP_KEY, textArea);
		widgets.add(new FieldLabel(textArea, BaseDatasourceMessages.INSTANCE.dataLabel()));
	}


	@Override
	public void inheritConnectorSpecificChanges(
			DatasourceDefinitionConfigDto config,
			DatasourceDefinitionDto datasourceDefinitionDto,
			CsvDatasourceConfigConfigurator csvDatasourceConfigConfigurator) {
		if(config instanceof FormatBasedDatasourceConfigDto){
			if(csvDatasourceConfigConfigurator.getDatamap().containsKey(TEXT_AREA_WIDGET_DATAMAP_KEY)){
				TextArea textArea = (TextArea) csvDatasourceConfigConfigurator.getDatamap().get(TEXT_AREA_WIDGET_DATAMAP_KEY);
				List<DatasourceConnectorConfigDto> connectorConfig = ((FormatBasedDatasourceConfigDto) config).getConnectorConfig();
				boolean found = false;
				for(DatasourceConnectorConfigDto ccfg : connectorConfig){
					if(CONNECTOR_CFG_KEY.equals(ccfg.getKey())){
						ccfg.setValue(textArea.getCurrentValue());
						found = true;
					}
				}
				if(!found){
					DatasourceConnectorConfigDto ccfg = new DatasourceConnectorConfigDto();
					ccfg.setKey(CONNECTOR_CFG_KEY);
					ccfg.setValue(textArea.getCurrentValue());
					connectorConfig.add(ccfg);
				}
			}
		}
	}

}
