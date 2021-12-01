package net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigExecution;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigToolbar;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeBasic;

/**
 * 
 *
 */
public class DatasourceSimpleFormProvider extends FormFieldProviderHookImpl {

	private final DatasourceUIService datasourceService;
	
	private DatasourceSelectionField datasourceFieldCreator;
	
	private final Provider<UITree> datasourceTreeProvider;
	
	@Inject
	public DatasourceSimpleFormProvider(
		DatasourceUIService datasourceService,
		@DatasourceTreeBasic Provider<UITree> datasourceTreeProvider
		){
		
		/* store objects */
		this.datasourceService = datasourceService;
		this.datasourceTreeProvider = datasourceTreeProvider;
	}
	
	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		return DatasourceContainerDto.class.equals(type);
	}

	public Widget createFormField() {
		Container wrapper = new VerticalLayoutContainer();
		
		datasourceFieldCreator = datasourceService.getSelectionField(wrapper, ! hasSupressConfigConfig(), datasourceTreeProvider);
		
		FieldLabel label = new FieldLabel();
		if(null != form.getSField(name).getFieldLayoutConfig().getLabelText())
			label.setText(form.getSField(name).getFieldLayoutConfig().getLabelText());
		if(null != form.getSField(name).getFieldLayoutConfig().getLabelAlign())
			label.setLabelAlign(form.getSField(name).getFieldLayoutConfig().getLabelAlign());
		datasourceFieldCreator.setFieldLabel(label);
		
		DatabaseSpecificFieldConfigExecution specificExecutionConfig = getSpecificExecutionConfig();
		if(null!=specificExecutionConfig)
			datasourceFieldCreator.addSpecificDatasourceConfig(specificExecutionConfig);

		DatabaseSpecificFieldConfigToolbar specificToolbarConfig = getSpecificToolbarConfig();
		if(null!=specificToolbarConfig)
			datasourceFieldCreator.addSpecificDatasourceConfig(specificToolbarConfig);
		
		datasourceFieldCreator.addSelectionField();
		if(! hasSupressDefaultConfig())
			datasourceFieldCreator.addDisplayDefaultButton();

		datasourceFieldCreator.addValueChangeHandler(new ValueChangeHandler<DatasourceContainerDto>() {
			@Override
			public void onValueChange(ValueChangeEvent<DatasourceContainerDto> event) {
				ValueChangeEvent.fire(DatasourceSimpleFormProvider.this, event.getValue());
			}
		});
		
		return wrapper;
	}
	
	private DatabaseSpecificFieldConfigExecution getSpecificExecutionConfig() {
		for(SimpleFormFieldConfiguration config : getConfigs())
			if(config instanceof SFFCDatasourceSpecificConfig)
				return ((SFFCDatasourceSpecificConfig)config).getConfigExecution();
		return null;
	}

	private DatabaseSpecificFieldConfigToolbar getSpecificToolbarConfig() {
		for(SimpleFormFieldConfiguration config : getConfigs())
			if(config instanceof SFFCDatasourceToolbarConfig)
				return ((SFFCDatasourceToolbarConfig)config).getFieldConfigToolbar();
		return null;
	}

	public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
		/* get datasource container */
		DatasourceContainerProviderDto datasourceContainerProvider;
		
		SFFCDatasourceMultipleContainerConfig multipleContainerConfig = getMultipleContainerConfig();
		
		if(null != multipleContainerConfig)
			datasourceContainerProvider = multipleContainerConfig.getSpecialDatasourceContainer((DwModel)model);
		else
			datasourceContainerProvider = (DatasourceContainerProviderDto)model;

		/* ask creator to init form binding */
		datasourceFieldCreator.initFormBinding(datasourceContainerProvider);
	}
	
	
	
	private SFFCDatasourceMultipleContainerConfig getMultipleContainerConfig() {
		for(SimpleFormFieldConfiguration config : getConfigs())
			if(config instanceof SFFCDatasourceMultipleContainerConfig)
				return (SFFCDatasourceMultipleContainerConfig) config;
		return null;
	}

	
	private boolean hasSupressConfigConfig() {
		for(SimpleFormFieldConfiguration config : getConfigs())
			if(config instanceof SFFCDatasourceSuppressConfig)
				return true;
		return false;
	}
	
	private boolean hasSupressDefaultConfig() {
		for(SimpleFormFieldConfiguration config : getConfigs())
			if(config instanceof SFFCDatasourceSuppressDefault)
				return true;
		return false;
	}

	public Object getValue(Widget field){
		return datasourceFieldCreator.getDatasourceContainer();
	}

	public void removeFieldBindings(Object model, Widget field) {
		throw new RuntimeException("not yet implemented"); //$NON-NLS-1$
	}
	
	@Override
	public boolean isDecorateable() {
		return false;
	}

}
