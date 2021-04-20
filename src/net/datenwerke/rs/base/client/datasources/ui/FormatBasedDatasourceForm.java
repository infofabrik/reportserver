package net.datenwerke.rs.base.client.datasources.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.FormatBasedDatasourceDefinitionDtoPA;
import net.datenwerke.rs.base.client.datasources.hooks.DatasourceConnectorConfiguratorHook;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;

public abstract class FormatBasedDatasourceForm extends SimpleFormView {

	@Inject
	protected HookHandlerService hookHandler;
	
	private Widget connectorForm;
	
	public void configureSimpleForm(SimpleForm form) {
		form.setHeading(DatasourcesMessages.INSTANCE.editDataSource() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		
		/* name name */
		form.addField(String.class, FormatBasedDatasourceDefinitionDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); 
		
		form.addField(String.class, FormatBasedDatasourceDefinitionDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(), new SFFCTextAreaImpl());		
		
		addSpecificFields(form);
		
		addConnector(form);
	}

	private void addConnector(SimpleForm form) {
		final List<DatasourceConnectorConfiguratorHook> configs = hookHandler.getHookers(DatasourceConnectorConfiguratorHook.class);
		
		form.setFieldWidth(0.3);
		
		final String listKey = form.addField(List.class, FormatBasedDatasourceDefinitionDtoPA.INSTANCE.connector(), BaseDatasourceMessages.INSTANCE.datasourceConnectorLabel(), new SFFCStaticDropdownList<DatasourceConnectorDto>() {

			private Map<String, DatasourceConnectorDto> map;
			
			@Override
			public Map<String, DatasourceConnectorDto> getValues() {
				if(null == map){
					map = new HashMap<String, DatasourceConnectorDto>();
					
					FormatBasedDatasourceDefinitionDto datasource = (FormatBasedDatasourceDefinitionDto)getSelectedNode();
					final DatasourceConnectorDto connector = datasource.getConnector();
					
					configs
                  .forEach(config -> map.put(config.getConnectorName(), 
                        null != connector &&  config.consumes(connector) ? connector : config.instantiateConnector()));
				}
				
				return map;
			}
		});
		
		form.setFieldWidth(1);
		
		final DwContentPanel specificsWrapper = DwContentPanel.newInlineInstance();
		
		form.addField(CustomComponent.class, new SFFCCustomComponent() {
			@Override
			public Widget getComponent() {
				return specificsWrapper;
			}
		});
		
		form.addCondition(listKey, new FieldChanged(), new SimpleFormAction() {
			public void onSuccess(SimpleForm form) {
				FormatBasedDatasourceDefinitionDto datasource = (FormatBasedDatasourceDefinitionDto)getSelectedNode();
				DatasourceConnectorDto connector = (DatasourceConnectorDto) form.getValue(listKey);
				datasource.setConnector(connector);
				
				if(null != connector){
					if(null != connectorForm)
						connectorForm.removeFromParent();
					
					for(DatasourceConnectorConfiguratorHook config : configs){
						if(config.consumes(connector)){
							connectorForm = config.configureForm(datasource);
							if(null != connectorForm){
								specificsWrapper.add(connectorForm, new MarginData(10));
							}
							break;
						}
					}
				}
			}
			
			public void onFailure(SimpleForm form) {
			}
		});
	}

	protected void addSpecificFields(SimpleForm form) {
		
	}

}
