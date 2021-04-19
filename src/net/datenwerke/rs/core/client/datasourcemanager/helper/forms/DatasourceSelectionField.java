package net.datenwerke.rs.core.client.datasourcemanager.helper.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.LayoutData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.TwinTriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TwinTriggerClickEvent.TwinTriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceContainerDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class DatasourceSelectionField implements HasValueChangeHandlers<DatasourceContainerDto> {

	public interface SpecificDatasourceConfig{
	}
	
	private static int FIELDNAME_INDEX = 1;
	private final String FIELDNAME = "__internal_datasource_" + FIELDNAME_INDEX++; //$NON-NLS-1$
	
	private final DatasourceUIService datasourceService;
	private final boolean displayOptionalAdditionalConfigFields;
	private final Container container;
	private final UITree datasourceTree;
	private final DatasourceDao datasourceDao;
	
	private final ClipboardUiService clipboardService;
	
	private DatasourceDefinitionDto oldDatasource = null;
	private SingleTreeSelectionField dsField;
	
	private Set<SpecificDatasourceConfig> specificDatasourceConfigs = new HashSet<DatasourceSelectionField.SpecificDatasourceConfig>();
	
	private DatasourceDefinitionConfigConfigurator datasourceConfigConfigurator;
	private DatasourceDefinitionConfigDto datasourceConfig;
	private DatasourceContainerDto datasourceContainer;

	private FieldLabel dsFieldLabel;
	private LayoutData fieldLayoutData;
	private VerticalLayoutContainer configContainer;
	private DatasourceTreeManagerDao datasourceTreeManager;
	private Class<? extends DatasourceDefinitionDto>[] types;
	
	private boolean shouldShowDefaultAdditionalConfig = false;
	
	@Inject
	public DatasourceSelectionField(
		ClipboardUiService clipboardService,
		DatasourceUIService datasourceService,
		DatasourceTreeManagerDao datasourceTreeManager,
		@Assisted boolean displayOptionalAdditionalConfigFields,
		@Assisted Container container, 
		@Assisted UITree datasourceTree,
		@Assisted DatasourceDao generalPropertiesDao, 
		@Assisted Class<? extends DatasourceDefinitionDto>... types
		){
		
		/* store objects */
		this.clipboardService = clipboardService;
		this.datasourceService = datasourceService;
		this.datasourceTreeManager = datasourceTreeManager;
		this.displayOptionalAdditionalConfigFields = displayOptionalAdditionalConfigFields;
		this.container = container;
		this.datasourceTree = datasourceTree;
		this.datasourceDao = generalPropertiesDao;
		this.types = types;
		
		if(null == this.types || this.types.length == 0){
			this.types = new Class[]{DatasourceDefinitionDto.class};
		}
	}
	
	public SingleTreeSelectionField getSelectionField(){
		return dsField;
	}
	
	public DatasourceDefinitionDto getDatasource(){
		return (DatasourceDefinitionDto) dsField.getValue();
	}
	
	public DatasourceContainerDto getDatasourceContainer() {
		if(null != datasourceContainer)
			return datasourceContainer;
		else {
			DatasourceContainerDto container = new DatasourceContainerDto();
			container.setDatasource(getDatasource());
			container.setDatasourceConfig(getDatasourceConfig());
			return container;
		}
	}
	
	public DatasourceDefinitionConfigDto getDatasourceConfig(){
		return datasourceConfig;
	}
	
	public void inheritChanges() {
		if(null != datasourceConfigConfigurator && null != datasourceConfig){
			datasourceConfigConfigurator.inheritChanges(datasourceConfig, datasourceContainer.getDatasource());
			
			ValueChangeEvent.fire(dsField, dsField.getValue());
		}
	}
	
	public void setFieldLabel(FieldLabel fieldLabel){
		if(null != dsField){
			dsFieldLabel.setText(fieldLabel.getText());
		} else
			this.dsFieldLabel = fieldLabel;
	}
	
	public void setLabel(String text) {
		if(null == text)
			return;
		
		if(null == dsFieldLabel)
			this.dsFieldLabel = new FieldLabel();

		this.dsFieldLabel.setText(text);
	}
	
	public void addSelectionField(){
		dsField = new SingleTreeSelectionField(types);
		dsField.setHideTwinTrigger(false);
		dsField.setTriggerIcon(BaseIcon.SEARCH);
		dsField.setTwinTriggerIcon(BaseIcon.DATABASE);
		dsField.setWidth(210);
		dsField.setTreePanel(datasourceTree);
		dsField.setName(FIELDNAME);
		
		if(null == dsFieldLabel)
			dsFieldLabel = new FieldLabel(dsField, DatasourcesMessages.INSTANCE.dataSource());
		else
			dsFieldLabel.setWidget(dsField);
		
		if(null != fieldLayoutData)
			dsFieldLabel.setLayoutData(fieldLayoutData);
		
		container.add(dsFieldLabel);
		
		/* clipboard */
		clipboardService.registerPasteHandler(dsField, new ClipboardDtoPasteProcessor(DatasourceDefinitionDto.class) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				dsField.setValue((AbstractNodeDto) dtoItem.getDto());
			}
		});
	}
	
	private void showFailureMessageBox() {
		MessageBox msg = new MessageBox(DatasourcesMessages.INSTANCE.useDefaultFailureTitle(), DatasourcesMessages.INSTANCE.useDefaultFailureMessage());
		msg.show();
	}
	
	
	public void addDisplayDefaultButton() {
		dsField.setTwinTriggerIcon(BaseIcon.DATABASE);
		dsField.setHideTwinTrigger(false);
		
		dsField.addTwinTriggerClickHandler(new TwinTriggerClickHandler() {
			@Override
			public void onTwinTriggerClick(TwinTriggerClickEvent event) {
				datasourceDao.getDefaultDatasource(new NotamCallback<DatasourceDefinitionDto>(DatasourcesMessages.INSTANCE.useDefaultSuccessMessage(), DatasourcesMessages.INSTANCE.useDefaultFailureMessage()) {
					@Override
					public void doOnSuccess(DatasourceDefinitionDto result) {
						if(result == null)
							showFailureMessageBox();
						else
							dsField.setValue(result, true);
					}	
				});
			}
		});
	}
	
	public HasValueFieldBinding initFormBinding(final DatasourceContainerProviderDto datasourceContainerProvider) {
		datasourceContainer = datasourceContainerProvider.getDatasourceContainer();
		if(null == datasourceContainer){
			datasourceContainer = new DatasourceContainerDto();
			datasourceContainerProvider.setDatasourceContainer(datasourceContainer);
		}
		
		HasValueFieldBinding binding = new HasValueFieldBinding(dsField, datasourceContainer, DatasourceContainerDtoPA.INSTANCE.datasource()){
			@Override  
			public void updateField() {
				DatasourceSelectionField.this.datasourceContainer = datasourceContainerProvider.getDatasourceContainer();
				if(null != datasourceContainer)
					dsField.setValue(datasourceContainer.getDatasource(), true);
				else
					dsField.setValue(null, true);
			}
			
			@Override
  		    public void updateModel(Object value) {
				DatasourceSelectionField.this.datasourceContainer = datasourceContainerProvider.getDatasourceContainer();
				
				/* get datasource */
				DatasourceDefinitionDto datasource = (DatasourceDefinitionDto) dsField.getValue();
				
				/* update fields */
				DatasourceSelectionField.this.updateFieldsAndModel(datasource, datasourceContainerProvider);
				
				ValueChangeEvent.fire(DatasourceSelectionField.this, datasourceContainer);
			}
		};

		return binding;
	}

	protected void updateFieldsAndModel(DatasourceDefinitionDto datasource, final DatasourceContainerProviderDto datasourceContainerProvider) {
		/* if datasource is null remove config and return */
		if(null == datasource){
			/* update model */
			datasourceConfig = null;
			oldDatasource = null;
			datasourceContainer.setDatasource(null);
			
			/* remove config fields */
			if(null != configContainer){
				configContainer.removeFromParent();
				configContainer = null;
			}
			return;
		}

		/* we have a new datasource */
		
		/* update model with new datasource */
		datasourceContainer.setDatasource(datasource);
		
		/* reset the datasource container, so that a proxy knows that something changed */
		datasourceContainerProvider.setDatasourceContainer(datasourceContainer); 
		
		/* if the new datasource equals the old one (type) and configurator does not want to reload then do nothing */
		DatasourceDefinitionConfigConfigurator tempConfigurator = datasourceService.getConfigurator(datasource.getClass());
		if(null != oldDatasource && datasource.getClass().equals(oldDatasource.getClass()) && ! tempConfigurator.isReloadOnDatasourceChange())
			return;
		else
			oldDatasource = datasource;
		
		/* load the configurator */
		datasourceConfigConfigurator = datasourceService.getConfigurator(datasource.getClass());
		
		/* should we use a new or an old datasource config */
		boolean dsContainerNotNull = null != datasourceContainer;
		boolean dsEqContainerDS = datasource.equals(datasourceContainer.getDatasource());
		boolean containerHasConfig = null != datasourceContainer.getDatasourceConfig();
		boolean configuratorConsumesDatasourceAndConfig = datasourceConfigConfigurator.consumes(datasourceContainer.getDatasource(), datasourceContainer.getDatasourceConfig());
		
		if(dsContainerNotNull && dsEqContainerDS && containerHasConfig && configuratorConsumesDatasourceAndConfig)
			datasourceConfig = datasourceContainer.getDatasourceConfig();
		else {
			datasourceConfig = datasourceConfigConfigurator.createConfigObject(datasource, datasourceContainerProvider);
			
			/* update model */
			datasourceContainer.setDatasourceConfig(datasourceConfig);
		}
		
		/* Remove old specialized fields and find index of form fields */
		
		if(null != configContainer){
			/* we have to go one step up */
			Widget configSet = configContainer.getParent();
			configSet.removeFromParent();
		}
		
		Iterable<Widget> dsDefaultAdditionalConfigFields = datasourceConfigConfigurator.getDefaultAdditionalFormfields(datasourceConfig, datasource, this, datasourceContainerProvider);
		
		
		/* we might be able to exit here, if we are not supposed to display the configurations special fields. */
		if(! displayOptionalAdditionalConfigFields && null == dsDefaultAdditionalConfigFields) 
			return;
		
		
		Iterable<Widget> dsOptionalAdditionalConfigFields = (displayOptionalAdditionalConfigFields ? 
				datasourceConfigConfigurator.getOptionalAdditionalFormfields(datasourceConfig, datasource, this, datasourceContainerProvider)
				: null);

		List<Widget> allAdditionalConfigFields = new ArrayList<>();
		if (null != dsDefaultAdditionalConfigFields && shouldShowDefaultAdditionalConfig)
			for (Widget w: dsDefaultAdditionalConfigFields)
				allAdditionalConfigFields.add(w);
		if (null != dsOptionalAdditionalConfigFields)
			for (Widget w: dsOptionalAdditionalConfigFields)
				allAdditionalConfigFields.add(w);
		
		if (allAdditionalConfigFields.isEmpty())
			return;
		
		Integer index = null;
		/* we first have to display the specialized fields, and can only then update the fields */
		if(allAdditionalConfigFields.iterator().hasNext()){
			SimpleContainer configSet = new SimpleContainer();

			configContainer = new VerticalLayoutContainer();
			configSet.add(configContainer);
			
			if(null == index && container instanceof InsertPanel && null != dsFieldLabel)
				index = container.getWidgetIndex(dsFieldLabel) + 1;
			
			if(allAdditionalConfigFields instanceof SimpleForm){
				configContainer.add((Widget)allAdditionalConfigFields);
			} else {
				for(Widget field : allAdditionalConfigFields){
					if(field instanceof FieldLabel && null != dsFieldLabel)
						((FieldLabel)field).setLabelAlign(dsFieldLabel.getLabelAlign());
					
					if(field instanceof Component && ! ((Component)field).isAutoWidth())
						configContainer.add(field, new VerticalLayoutData(-1, -1, new Margins(0, 0, 10, 0)));
					else if(field instanceof FieldLabel && null != ((FieldLabel)field).getWidget() && ((FieldLabel)field).getWidget() instanceof Component && ! ((Component)((FieldLabel)field).getWidget()).isAutoWidth()) 
						configContainer.add(field, new VerticalLayoutData(-1, -1, new Margins(0, 0, 10, 0)));
					else
						configContainer.add(field, new VerticalLayoutData(1, -1, new Margins(0, 0, 10, 0)));
				}
			}
			
			if(null != index && index >= 0 && container instanceof InsertPanel)
				((InsertPanel)container).insert(configSet, index++);
			else
				container.add(configSet);
		}
	}

	public void setSpecificDatasourceConfigs(
			Set<SpecificDatasourceConfig> specificDatasourceConfigs) {
		this.specificDatasourceConfigs = specificDatasourceConfigs;
	}

	public Set<SpecificDatasourceConfig> getSpecificDatasourceConfigs() {
		return specificDatasourceConfigs;
	}

	public void addSpecificDatasourceConfig(SpecificDatasourceConfig config){
		this.specificDatasourceConfigs.add(config);
	}

	public void setFieldLayoutData(LayoutData fieldLayoutData) {
		this.fieldLayoutData = fieldLayoutData;
	}
	
	public void updateDatasourceConfig() {
		/* reload config from container and set changes */
		datasourceConfig = datasourceContainer.getDatasourceConfig();
		inheritChanges();
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		EventBusHelper.EVENT_BUS.fireEventFromSource(event, this);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<DatasourceContainerDto> handler) {
		return EventBusHelper.EVENT_BUS.addHandlerToSource(ValueChangeEvent.getType(), this, handler);
	}

	public boolean isShouldShowDefaultAdditionalConfig() {
		return shouldShowDefaultAdditionalConfig;
	}

	public void setShouldShowDefaultAdditionalConfig(boolean shouldShowDefaultAdditionalConfig) {
		this.shouldShowDefaultAdditionalConfig = shouldShowDefaultAdditionalConfig;
	}

}
