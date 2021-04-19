package net.datenwerke.rs.base.client.datasources.config;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.ui.helper.misc.DynamicGridWindow;
import net.datenwerke.gxtdto.client.ui.helper.misc.DynamicGridWindow.DataProvider;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField.SpecificDatasourceConfig;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatabaseDatasourceConfigConfigurator implements
		DatasourceDefinitionConfigConfigurator {

	public interface DatabaseSpecificFieldConfigToolbar extends SpecificDatasourceConfig{
		public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel);
	}
	
	public interface DatabaseSpecificFieldConfigExecution extends SpecificDatasourceConfig{
		public void executeGetColumns(String query, AsyncCallback<List<String>> asyncCallback);
		public void executeGetData(PagingLoadConfig loadConfig, String query, AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback);
	}
	
	private final ToolbarService toolbarService;

	private HasValue<String> queryField;
	
	@Inject
	public DatabaseDatasourceConfigConfigurator(
		ToolbarService toolbarService) {
		this.toolbarService = toolbarService;
	}

	@Override
	public List<Widget> getOptionalAdditionalFormfields(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto, final DatasourceSelectionField datasourceSelectionField, final DatasourceContainerProviderDto datasourceContainerProvider) {
		if(! (config instanceof DatabaseDatasourceConfigDto))
			throw new IllegalArgumentException("Expected " + DatabaseDatasourceConfigDto.class.getName()); //$NON-NLS-1$
		
		/* get enhancer */
		final DatabaseSpecificFieldConfigToolbar toolbarSpecConfig = getSpecificConfigToolbar(datasourceSelectionField);
		final DatabaseSpecificFieldConfigExecution executionSpecConfig = getSpecificConfigExecution(datasourceSelectionField);
		ToolBarEnhancer enhancer = new ToolBarEnhancer() {
			
			@Override
			public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel) {
				if(null != executionSpecConfig){
					/* execute */
					DwTextButton executeBtn = toolbarService.createSmallButtonLeft(DatasourcesMessages.INSTANCE.execute(), BaseIcon.COG);
					toolbar.add(executeBtn);
					executeBtn.addSelectHandler(new SelectHandler() {
						@Override
						public void onSelect(SelectEvent event) {
							executeQuery(executionSpecConfig, codeMirrorPanel.getTextArea().getValue());
						}
					});
				}
				
				if(null != toolbarSpecConfig)
					toolbarSpecConfig.enhance(toolbar, codeMirrorPanel);
			}
			
			@Override
			public boolean allowPopup() {
				return true;
			}
		};
		
		queryField = new CodeMirrorPanel("text/x-sql", enhancer);
		queryField.setValue(((DatabaseDatasourceConfigDto)config).getQuery());
		if(queryField instanceof CodeMirrorPanel)
			((CodeMirrorPanel)queryField).setHeight(200);
		else if(queryField instanceof TextArea)
			((TextArea)queryField).setHeight(200);
		
		queryField.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				datasourceSelectionField.updateDatasourceConfig();
			}
		});
		
		return Arrays.asList(new Widget[]{new FieldLabel((Widget)queryField, DatasourcesMessages.INSTANCE.query())});
	}

	private DatabaseSpecificFieldConfigToolbar getSpecificConfigToolbar(
			DatasourceSelectionField datasourceSelectionField) {
		for(SpecificDatasourceConfig conf : datasourceSelectionField.getSpecificDatasourceConfigs())
			if(conf instanceof DatabaseSpecificFieldConfigToolbar)
				return (DatabaseSpecificFieldConfigToolbar) conf;
		return null;
	}
	
	private DatabaseSpecificFieldConfigExecution getSpecificConfigExecution(
			DatasourceSelectionField datasourceSelectionField) {
		for(SpecificDatasourceConfig conf : datasourceSelectionField.getSpecificDatasourceConfigs())
			if(conf instanceof DatabaseSpecificFieldConfigExecution)
				return (DatabaseSpecificFieldConfigExecution) conf;
		return null;
	}

	@Override
	public void inheritChanges(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto) {
		if(! (config instanceof DatabaseDatasourceConfigDto))
			throw new IllegalArgumentException("Expected " + DatabaseDatasourceConfigDto.class.getName()); //$NON-NLS-1$

		/* are we rendered */
		if(null == queryField)
			((DatabaseDatasourceConfigDto)config).setQuery(null);
		else
			((DatabaseDatasourceConfigDto)config).setQuery(queryField.getValue());
	}

	@Override
	public DatasourceDefinitionConfigDto createConfigObject(DatasourceDefinitionDto datasourceDefinitionDto, DatasourceContainerProviderDto datasourceContainerProvider) {
		return new DatabaseDatasourceConfigDto();
	}
	
	@Override
	public boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto, DatasourceDefinitionConfigDto datasourceConfig) {
		return datasourceDefinitionDto instanceof DatabaseDatasourceDto && datasourceConfig instanceof DatabaseDatasourceConfigDto;
	}
	

	protected void executeQuery(final DatabaseSpecificFieldConfigExecution specConfig, final String value) {
		if(null == value || "".equals(value.trim()))
			return;
		
		DynamicGridWindow window = new DynamicGridWindow(new DataProvider(){
			@Override
			public void getData(
					PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {
				specConfig.executeGetData((PagingLoadConfig) loadConfig, value, callback);
			}

			@Override
			public void getColumns(AsyncCallback<List<String>> asyncCallback) {
				specConfig.executeGetColumns(value, asyncCallback);
			}
		});
		
		window.show();
	}

	@Override
	public boolean isValid(DatasourceContainerDto container) {
		if(! consumes(container.getDatasource(), container.getDatasourceConfig()))
			return false;
		
		DatabaseDatasourceConfigDto config = (DatabaseDatasourceConfigDto) container.getDatasourceConfig();
		
		if(null == config.getQuery() || "".equals(config.getQuery().trim()))
			return false;
		
		return true;
	}
	
	@Override
	public boolean isReloadOnDatasourceChange() {
		return false;
	}

	@Override
	public Iterable<Widget> getDefaultAdditionalFormfields(DatasourceDefinitionConfigDto config,
			DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField,
			DatasourceContainerProviderDto datasourceContainerProvider) {
		return null;
	}

}
