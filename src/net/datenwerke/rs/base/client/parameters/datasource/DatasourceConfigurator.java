package net.datenwerke.rs.base.client.parameters.datasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormSuccessAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.ComplexCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.CompositeAndCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.CompositeOrCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCDynamicListInPopup;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEditableDropDown;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigExecution;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigToolbar;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterDataDtoPA;
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.ui.model.SpecialParameters;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform.SFFCDatasourceSpecificConfig;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform.SFFCDatasourceToolbarConfig;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.globalconstants.client.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.incubator.client.reportmetadata.locale.ReportMetadataMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DatasourceConfigurator extends ParameterConfiguratorImpl<DatasourceParameterDefinitionDto, DatasourceParameterInstanceDto> {

	private final DatasourceParameterDao datasourceParamDao;
	private final Provider<DatasourceEditComponentForInstance> editComponentForInstanceProvider;
	private final DatasourceParameterUiService dsParamService;
	private final TableReportUtilityDao tableReportDao;
	private final EnterpriseUiService entpriseService;
	private final ToolbarService toolbarService;
		
	
	private String singleDefaultValuesKey;
	private String multiDefaultValuesKey;
	private ListStore<DatasourceParameterDataDto> parameterDataStore;
	private DatasourceEditComponentForInstance editComponentConfigurator;
	private ScheduledCommand reloadCommand;
	
	@Inject
	public DatasourceConfigurator(
			DatasourceParameterDao rpcService,
		Provider<DatasourceEditComponentForInstance> editComponentForInstanceProvider,
		DatasourceParameterUiService dsParamService,
		EnterpriseUiService entpriseService,
		TableReportUtilityDao tableReportDao,
		ToolbarService toolbarService
		){
		
		/* store objects */
		this.datasourceParamDao = rpcService;
		this.editComponentForInstanceProvider = editComponentForInstanceProvider;
		this.dsParamService = dsParamService;
		this.entpriseService = entpriseService;
		this.tableReportDao = tableReportDao;
		this.toolbarService = toolbarService;
	}
	

	public String getName() {
		return RsMessages.INSTANCE.datasourceParameter(); 
	}

	@Override
	protected DatasourceParameterDefinitionDto doGetNewDto() {
		DatasourceParameterDefinitionDto par = new DatasourceParameterDefinitionDto();
		par.setHeight(1);
		par.setWidth(550);
		return par;
	}
	
	@Override
	public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
		return DatasourceParameterDefinitionDto.class.equals(type);
	}

	public ImageResource getIcon() {
		return BaseIcon.DATABASE.toImageResource();
	}
	
	private void addSpecialParameter(final CodeMirrorPanel codeMirrorPanel,
			final Menu subMenuUser, final MenuItem subMenuItemUser, 
			final Menu subMenuReport, final MenuItem subMenuItemReport, 
			final Menu subMenuLocale, final MenuItem subMenuItemLocale, 
			final Menu subMenuGlobalConstants, final MenuItem subMenuItemGlobalConstants,
			final Menu subMenuMetadata, final MenuItem subMenuItemMetadata,
			final Menu subMenuExpressions, final MenuItem subMenuItemExpressions,
			final TableReportDto report) {
		

		tableReportDao.getSpecialParameter(report, null, new RsAsyncCallback<Map<String, List<String>>>(){
			@Override
			public void onSuccess(Map<String, List<String>> result) {
				
				for (Entry<String, List<String>> entry : result.entrySet()) {
					String paramKey = entry.getKey();
					
					for (final String itemKey : entry.getValue()) {					
						MenuItem item = new MenuItem(itemKey);
						item.addSelectionHandler(new SelectionHandler<Item>() {
							@Override
							public void onSelection(SelectionEvent<Item> event) {
								String value = codeMirrorPanel.getTextArea().getValue();
								value = null == value ? "" : value;
								value = itemKey.startsWith("$") ? value + itemKey : value + "${" + itemKey + "}";
								codeMirrorPanel.getTextArea().setValue(value);
							}
						});
						
						if (SpecialParameters._RS_USER.name().equals(paramKey)) {
							subMenuUser.add(item);				
						}
						
						if (SpecialParameters._RS_REPORT.name().equals(paramKey)) {
							subMenuReport.add(item);				
						}
						
						if (SpecialParameters._RS_LOCALE.name().equals(paramKey)) {
							subMenuLocale.add(item);			
						}

						if (SpecialParameters._RS_GLOBAL_CONSTANTS.name().equals(paramKey)) {
							subMenuGlobalConstants.add(item);			
						}

						if (SpecialParameters._RS_METADATA.name().equals(paramKey)) {
							item.setText(itemKey.replace(SpecialParameters._RS_METADATA.name() + "_", ""));
							subMenuMetadata.add(item);			
						}
						
						if (null != subMenuExpressions && SpecialParameters._RS_EXPRESSION.name().equals(paramKey)) {
							subMenuExpressions.add(item);			
						}
					}
					
				}
				
				subMenuItemUser.setSubMenu(subMenuUser);
				subMenuItemLocale.setSubMenu(subMenuLocale);
				subMenuItemReport.setSubMenu(subMenuReport);
				subMenuItemGlobalConstants.setSubMenu(subMenuGlobalConstants);
				subMenuItemMetadata.setSubMenu(subMenuMetadata);
				if (null != subMenuExpressions && null != subMenuItemExpressions)
					subMenuItemExpressions.setSubMenu(subMenuExpressions);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
			}
			
		});
	}

	@Override
	public Widget getEditComponentForDefinition(final DatasourceParameterDefinitionDto definition, final ReportDto report) {
		final SimpleForm form = SimpleForm.getInlineInstance();
		
		SFFCDatasourceSpecificConfig specificDatasourceConfig = new SFFCDatasourceSpecificConfig() {
	
			@Override
			public DatabaseSpecificFieldConfigExecution getConfigExecution() {
				return new DatabaseSpecificFieldConfigExecution(){
					@Override
					public void executeGetColumns(String query, final AsyncCallback<List<String>> callback) {
						/* fake report */
						DatasourceContainerDto dsContainer = (DatasourceContainerDto) form.getValue(DatasourceParameterDefinitionDtoPA.INSTANCE.datasourceContainer().getPath());
						
						tableReportDao.loadColumnDefinition(report, dsContainer, query, null, new RsAsyncCallback<List<ColumnDto>>(){
							@Override
							public void onSuccess(List<ColumnDto> result) {
								List<String> columns = new ArrayList<String>();
								for(ColumnDto col : result)
									columns.add(col.getName());
								callback.onSuccess(columns);
							}
							@Override
							public void onFailure(Throwable caught) {
								callback.onFailure(caught);
							}
							
						});
					}
					
					@Override
					public void executeGetData(
							PagingLoadConfig loadConfig,
							String query,
							AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {
						DatasourceContainerDto dsContainer = (DatasourceContainerDto) form.getValue(DatasourceParameterDefinitionDtoPA.INSTANCE.datasourceContainer().getPath());
						
						tableReportDao.loadData(report, dsContainer, loadConfig, query, callback);
					}
	
				};
			}
			
		};
		
		SFFCDatasourceToolbarConfig toolbarDatasourceConfig  = new SFFCDatasourceToolbarConfig(){
	
			@Override
			public DatabaseSpecificFieldConfigToolbar getFieldConfigToolbar() {
				return new DatabaseSpecificFieldConfigToolbar() {
					@Override
					public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel) {
						/* add parameter */
						DwTextButton selectParamBtn = toolbarService.createSmallButtonLeft(ReportmanagerMessages.INSTANCE.selectParamBtnLabel(), BaseIcon.TABLE_ADD);
						selectParamBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
						toolbar.add(selectParamBtn);
						
						final Menu menu = new DwMenu();
						selectParamBtn.setMenu(menu);
						
						menu.addBeforeShowHandler(new BeforeShowHandler() {					
							@Override
							public void onBeforeShow(BeforeShowEvent event) {
								menu.clear();
								addReportParameter(codeMirrorPanel, menu);
								
								SeparatorMenuItem separator = new SeparatorMenuItem();
								menu.add(separator);
								
								final Menu subMenuUser = new DwMenu();
								final MenuItem subMenuItemUser = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentUser());
								menu.add(subMenuItemUser);
								
								final Menu subMenuReport = new DwMenu();
								final MenuItem subMenuItemReport = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentReport());
								menu.add(subMenuItemReport);
								
								final Menu subMenuLocale = new DwMenu();
								final MenuItem subMenuItemLocale = new DwMenuItem("Locale");
								menu.add(subMenuItemLocale);
								
								final Menu subMenuGlobalConstants = new DwMenu();
								final MenuItem subMenuItemGlobalConstants = new DwMenuItem(GlobalConstantsMessages.INSTANCE.securityTitle());
								menu.add(subMenuItemGlobalConstants);
	
								final Menu subMenuMetadata = new DwMenu();
								final MenuItem subMenuItemMetadata = new DwMenuItem(ReportMetadataMessages.INSTANCE.header());
								menu.add(subMenuItemMetadata);
								
								final Menu subMenuExpressions = new DwMenu();
								final MenuItem subMenuItemExpressions = new DwMenuItem(ReportmanagerMessages.INSTANCE.expressions());
								menu.add(subMenuItemExpressions);
								
								addSpecialParameter(codeMirrorPanel, subMenuUser, subMenuItemUser, subMenuReport,
										subMenuItemReport, subMenuLocale, subMenuItemLocale, subMenuGlobalConstants,
										subMenuItemGlobalConstants, subMenuMetadata, subMenuItemMetadata, 
										subMenuExpressions, subMenuItemExpressions, (TableReportDto) report);
							}
							
							private void addReportParameter(final CodeMirrorPanel codeMirrorPanel, final Menu menu) {
								for(ParameterDefinitionDto def : report.getParameterDefinitions()){
									final String key = def.getKey(); 
									MenuItem item = new DwMenuItem(def.getName());
									item.addSelectionHandler(new SelectionHandler<Item>() {
										@Override
										public void onSelection(SelectionEvent<Item> event) {
											String value = codeMirrorPanel.getTextArea().getValue();
											value = null == value ? "" : value;
											value = key.startsWith("$") ? value + key : value + "${" + key + "}";					
											codeMirrorPanel.getTextArea().setValue(value);
										}
									});
									menu.add(item);
								}
							}
						});
					}
				};
				
				
			}
			
		};		

		/* data source */
		String dataOriginDatasourceKey = form.addField(
				DatasourceContainerDto.class,
				DatasourceParameterDefinitionDtoPA.INSTANCE.datasourceContainer(), RsMessages.INSTANCE.datasource(),
				specificDatasourceConfig, toolbarDatasourceConfig);
		
		String postProcessKey = "";
		if(dsParamService.isAllowPostProcessing()){
			form.setLabelAlign(LabelAlign.TOP);
			form.setLabelWidth(100);
			postProcessKey = form.addField(String.class, DatasourceParameterDefinitionDtoPA.INSTANCE.postProcess(), RsMessages.INSTANCE.datasourceParameterPostProcess(), new SFFCTextAreaImpl(){
				@Override
				public int getHeight() {
					return 75;
				}
			});
		}
		
		form.setLabelAlign(LabelAlign.TOP);
		form.beginFloatRow();
		form.setFieldWidth(210);
		form.addField(Integer.class, DatasourceParameterDefinitionDtoPA.INSTANCE.width(), BaseMessages.INSTANCE.width());
		form.addField(Integer.class, DatasourceParameterDefinitionDtoPA.INSTANCE.height(), BaseMessages.INSTANCE.height());
		form.endRow();
		
		/* add mode */
		form.beginFloatRow();
		form.setFieldWidth(210);
		final String modeKey = form.addField(
			List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.mode(), RsMessages.INSTANCE.seletionMode(), 
			new SFFCStaticDropdownList<ModeDto>() {
				public Map<String, ModeDto> getValues() {
					Map<String, ModeDto> map = new HashMap<String, ModeDto>();
					
					map.put(RsMessages.INSTANCE.singleSelect(), ModeDto.Single);
					map.put(RsMessages.INSTANCE.multiSelect(), ModeDto.Multi);
					
					return map;
				}
		});
		
		final String singleSelectionModeKey = form.addField(
				List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.singleSelectionMode(), RsMessages.INSTANCE.singleSeletionMode(), 
				new SFFCStaticDropdownList<SingleSelectionModeDto>() {
					public Map<String, SingleSelectionModeDto> getValues() {
						Map<String, SingleSelectionModeDto> map = new HashMap<String, SingleSelectionModeDto>();
						
						map.put(RsMessages.INSTANCE.dropdown(), SingleSelectionModeDto.Dropdown);
						map.put(RsMessages.INSTANCE.popup(), SingleSelectionModeDto.Popup);
						map.put(RsMessages.INSTANCE.radio(), SingleSelectionModeDto.Radio);
						map.put(RsMessages.INSTANCE.listbox(), SingleSelectionModeDto.Listbox);
						
						return map;
					}
			});
		
		final String multiSelectionModeKey = form.addField(
				List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.multiSelectionMode(), RsMessages.INSTANCE.multiSeletionMode(), 
				new SFFCStaticDropdownList<MultiSelectionModeDto>() {
					public Map<String, MultiSelectionModeDto> getValues() {
						Map<String, MultiSelectionModeDto> map = new HashMap<String, MultiSelectionModeDto>();
						
						map.put(RsMessages.INSTANCE.popup(), MultiSelectionModeDto.Popup);
						map.put(RsMessages.INSTANCE.checkbox(), MultiSelectionModeDto.Checkbox);
						map.put(RsMessages.INSTANCE.listbox(), MultiSelectionModeDto.Listbox);
						
						return map;
					}
			});
		form.endRow();
		
		form.beginFloatRow();
		
		
		final String boxModeKey = form.addField(
				List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutMode(), RsMessages.INSTANCE.boxLayoutMode(), 
				new SFFCStaticDropdownList<BoxLayoutModeDto>() {
					public Map<String, BoxLayoutModeDto> getValues() {
						Map<String, BoxLayoutModeDto> map = new HashMap<String, BoxLayoutModeDto>();
						
						map.put(RsMessages.INSTANCE.boxLayoutLrTd(), BoxLayoutModeDto.LeftRightTopDown);
						map.put(RsMessages.INSTANCE.boxLayoutTdLr(), BoxLayoutModeDto.TopDownLeftRight);
						
						return map;
					}
			});
		
		form.setFieldWidth(100);
		final String boxPackModeKey = form.addField(
				List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutPackMode(), RsMessages.INSTANCE.boxLayoutPackMode(), 
				new SFFCStaticDropdownList<BoxLayoutPackModeDto>() {
					public Map<String, BoxLayoutPackModeDto> getValues() {
						Map<String, BoxLayoutPackModeDto> map = new HashMap<String, BoxLayoutPackModeDto>();
						
						map.put(RsMessages.INSTANCE.columns(), BoxLayoutPackModeDto.Columns);
						map.put(RsMessages.INSTANCE.packages(), BoxLayoutPackModeDto.Packages);
						
						return map;
					}
			});
		
		final String boxPackColSizeKey = form.addField(
				Integer.class, DatasourceParameterDefinitionDtoPA.INSTANCE.boxLayoutPackColSize(), RsMessages.INSTANCE.boxLayoutPackColSize() 
		);
		
		form.endRow();
		
		
		/* add type */
		form.setFieldWidth(210);
		final String typeKey = form.addField(
			List.class, DatasourceParameterDefinitionDtoPA.INSTANCE.returnType(), RsMessages.INSTANCE.returnType(), 
			new SFFCStaticDropdownList<DatatypeDto>() {
				@Override
				public Map<String, DatatypeDto> getValues() {
					Map<String, DatatypeDto> map = new TreeMap<String, DatatypeDto>();
					
					map.put("java.lang.String", DatatypeDto.String);
					map.put("java.lang.Integer", DatatypeDto.Integer);
					map.put("java.lang.Long", DatatypeDto.Long);
					map.put("java.lang.Double", DatatypeDto.Double);
					map.put("java.lang.Float", DatatypeDto.Float);
					map.put("java.lang.Boolean", DatatypeDto.Boolean);
					map.put("java.math.BigDecimal", DatatypeDto.BigDecimal);
					map.put("java.util.Date", DatatypeDto.Date);

					return map;
				}
		});
		
		form.setFieldWidth(1);
		String formatKey = form.addField(String.class, DatasourceParameterDefinitionDtoPA.INSTANCE.format(), RsMessages.INSTANCE.format());
		
		form.setFieldWidth(210);
		/* default value */
		singleDefaultValuesKey = form.addField(
			List.class, 
			DatasourceParameterDefinitionDtoPA.INSTANCE.singleDefaultValueSimpleData(),
			RsMessages.INSTANCE.defaultValues(),
			new SFFCDynamicListInPopup<DatasourceParameterDataDto>() {
				public ListStore<DatasourceParameterDataDto> getStore() {
					return getParameterDataStore(definition, report);
				}
				
				public String getPropertyLabel() {
					return RsMessages.INSTANCE.defaultValues();
				};
				
				public ValueProvider getStoreKeyForDisplay() {
					return DatasourceParameterDataDtoPA.INSTANCE.key();
				}

				public Boolean isMultiselect() {
					return false;
				}
			},new SFFCEditableDropDown(){}
		);
		
		form.setFieldWidth(1);
		multiDefaultValuesKey = form.addField(
				List.class, 
				DatasourceParameterDefinitionDtoPA.INSTANCE.multiDefaultValueSimpleData(),
				RsMessages.INSTANCE.defaultValues(),
				new SFFCDynamicListInPopup<DatasourceParameterDataDto>() {
					public ListStore<DatasourceParameterDataDto> getStore() {
						return getParameterDataStore(definition, report);
					}
					
					public String getPropertyLabel() {
						return RsMessages.INSTANCE.defaultValues();
					};
					
					public ValueProvider getStoreKeyForDisplay() {
						return DatasourceParameterDataDtoPA.INSTANCE.key();
					}

					public Boolean isMultiselect() {
						return true;
					}
				} 
			);
			
		
		/* add dependency */
		form.addCondition(dataOriginDatasourceKey, new FieldChanged(), new SimpleFormSuccessAction() {
			@Override
			public void onSuccess(SimpleForm form) {
				reloadDefault(modeKey, multiDefaultValuesKey, singleDefaultValuesKey, form);
			}
		});
		form.addCondition(modeKey, new FieldChanged(), new SimpleFormSuccessAction() {
			@Override
			public void onSuccess(SimpleForm form) {
				reloadDefault(modeKey, multiDefaultValuesKey, singleDefaultValuesKey, form);
			}
		});
		if(dsParamService.isAllowPostProcessing()){
			form.addCondition(postProcessKey, new FieldChanged(), new SimpleFormSuccessAction() {
				@Override
				public void onSuccess(SimpleForm form) {
					reloadDefault(modeKey, multiDefaultValuesKey, singleDefaultValuesKey, form);
				}
			});
		}
		
		
		CompositeOrCondition radioCheckCond = new CompositeOrCondition(
			new CompositeAndCondition(
				new ComplexCondition(modeKey, new FieldEquals(ModeDto.Single)),
				new ComplexCondition(singleSelectionModeKey, new FieldEquals(SingleSelectionModeDto.Radio))
			),
			new CompositeAndCondition(
				new ComplexCondition(modeKey, new FieldEquals(ModeDto.Multi)),
				new ComplexCondition(multiSelectionModeKey, new FieldEquals(MultiSelectionModeDto.Checkbox))
			)
		);
		
		form.addCondition(modeKey, radioCheckCond, new ShowHideFieldAction(boxModeKey));
		form.addCondition(modeKey, radioCheckCond, new ShowHideFieldAction(boxPackModeKey));
		form.addCondition(modeKey, radioCheckCond, new ShowHideFieldAction(boxPackColSizeKey));
		
		form.addCondition(singleSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxModeKey));
		form.addCondition(singleSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxPackModeKey));
		form.addCondition(singleSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxPackColSizeKey));
		
		form.addCondition(multiSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxModeKey));
		form.addCondition(multiSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxPackModeKey));
		form.addCondition(multiSelectionModeKey, radioCheckCond, new ShowHideFieldAction(boxPackColSizeKey));
		
		form.addCondition(modeKey, new FieldEquals(ModeDto.Single), new ShowHideFieldAction(singleSelectionModeKey));
		
		
		form.addCondition(modeKey, new FieldEquals(ModeDto.Single), new ShowHideFieldAction(singleSelectionModeKey));
		form.addCondition(modeKey, new FieldEquals(ModeDto.Multi), new ShowHideFieldAction(multiSelectionModeKey));
		form.addCondition(typeKey, new FieldEquals(DatatypeDto.Date), new ShowHideFieldAction(formatKey));

		form.addCondition(modeKey, new FieldEquals(ModeDto.Single), new ShowHideFieldAction(singleDefaultValuesKey));
		form.addCondition(modeKey, new FieldEquals(ModeDto.Multi), new ShowHideFieldAction(multiDefaultValuesKey));

		
		form.addCondition(modeKey, new FieldChanged(), new SimpleFormAction() {
			
			@Override
			public void onSuccess(SimpleForm form) {
				ModeDto mode = (ModeDto) form.getValue(modeKey);
				if(ModeDto.Multi.equals(mode)){
					if(1 == definition.getHeight())
						definition.setHeight(300);
				} else {
					definition.setHeight(1);
				}
			}
			
			@Override
			public void onFailure(SimpleForm form) {
			}
		});

		/* bind definition */
		form.bind(definition);
		
		/* return form */
		return form;
	}
	
	protected ListStore<DatasourceParameterDataDto> getParameterDataStore(final DatasourceParameterDefinitionDto definition, final ReportDto report) {
		if(null != parameterDataStore)
			return parameterDataStore;
		
		RpcProxy<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>>() {
			
			public void load(ListLoadConfig loadConfig, final AsyncCallback<ListLoadResult<DatasourceParameterDataDto>> callback) {
				AsyncCallback<ListLoadResult<DatasourceParameterDataDto>> cb = new AsyncCallback<ListLoadResult<DatasourceParameterDataDto>>() {
					
					@Override
					public void onSuccess(ListLoadResult<DatasourceParameterDataDto> result) {
						callback.onSuccess(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						new SimpleErrorDialog(caught.getMessage()).show();
					}
				};
				
				if((null == definition.getDatasourceContainer() || null == definition.getDatasourceContainer().getDatasource()) &&
					(! dsParamService.isAllowPostProcessing() || null == definition.getPostProcess() || "".equals(definition.getPostProcess().trim())))
					cb.onSuccess(new ListLoadResultBean<DatasourceParameterDataDto>(new ArrayList<DatasourceParameterDataDto>()));
				else
					datasourceParamDao.loadDatasourceParameterData((DatasourceParameterDefinitionDto) definition, null, true, report, cb);
			}
		};
		
		ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> listLoader = new ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>>(proxy);
		ListStore<DatasourceParameterDataDto> store = new LoadableListStore<ListLoadConfig, DatasourceParameterDataDto, ListLoadResult<DatasourceParameterDataDto>>(new BasicObjectModelKeyProvider(), listLoader);
		
		this.parameterDataStore = store;
		
		return store;
	}


	protected void reloadDefault(final String modeKey,
			final String multiDefaultValuesKey, final String singleDefaultValuesKey, final SimpleForm form) {
		if(null == reloadCommand){
			reloadCommand = new ScheduledCommand() {
				@Override
				public void execute() {
					reloadCommand = null;
					ModeDto mode = (ModeDto) form.getValue(modeKey);
					if(! form.isVisible() || ! form.isFieldsLoaded())
						return;
					if(ModeDto.Multi.equals(mode))
						form.reloadField(multiDefaultValuesKey);
					else
						form.reloadField(singleDefaultValuesKey);
				}
			};
			Scheduler.get().scheduleFinally(reloadCommand);
		}
	}


	@Override
	public Widget doGetEditComponentForInstance(DatasourceParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances, DatasourceParameterDefinitionDto definition, boolean initial, int labelWidth, String executeReportToken, final ReportDto report) {
		/* store configurator for later use -- when a parameter is redrawn due to a change for example in dependent parameters this will be overwritten */
		editComponentConfigurator = editComponentForInstanceProvider.get();
		return editComponentConfigurator.getEditComponent(definition, instance, relevantInstances, labelWidth, initial, report);
	}
	
	@Override
	public boolean canHandle(ParameterProposalDto proposal) {
		String type = proposal.getType();
		if(List.class.getName().equals(type))
			return true;
		if(Boolean.class.getName().equals(type))
			return true;

		return false;
	}

	@Override
	public ParameterDefinitionDto getNewDto(ParameterProposalDto proposal, ReportDto report) {
		DatasourceParameterDefinitionDto definition = (DatasourceParameterDefinitionDto) getNewDto(report);

		if(Boolean.class.getName().equals(proposal.getType())){
			definition.setMode(ModeDto.Single);
			definition.setSingleSelectionMode(SingleSelectionModeDto.Radio);
			definition.setReturnType(DatatypeDto.Boolean);
			definition.setBoxLayoutMode(BoxLayoutModeDto.LeftRightTopDown);
			definition.setBoxLayoutPackMode(BoxLayoutPackModeDto.Columns);
			definition.setBoxLayoutPackColSize(1);
			
			if(entpriseService.isEnterprise())
				definition.setPostProcess("[['true',true],['false',false]]");
		}
			
		
		return definition;
	}

	@Override
	public void dependeeInstanceChanged(DatasourceParameterInstanceDto instance,
			DatasourceParameterDefinitionDto aDefinition,
			Collection<ParameterInstanceDto> relevantInstances) {
		super.dependeeInstanceChanged(instance, aDefinition, relevantInstances);
		
		boolean silent = instance.isSilenceEvents();
		instance.silenceEvents(true);
		
		instance.setMultiValue(new ArrayList<DatasourceParameterDataDto>());
		instance.setSingleValue(null);
		
		instance.silenceEvents(silent);
	}
	
	@Override
	public boolean canDependOnParameters(){
		return true;
	}
	
	@Override
	public List<String> validateParameter(
			DatasourceParameterDefinitionDto definition,
			DatasourceParameterInstanceDto instance, Widget widget) {
		if(null == editComponentConfigurator)
			throw new IllegalStateException("Edit component should have been initialized");
		
		return editComponentConfigurator.validateParameter(definition, instance, widget);
	}
	
}
