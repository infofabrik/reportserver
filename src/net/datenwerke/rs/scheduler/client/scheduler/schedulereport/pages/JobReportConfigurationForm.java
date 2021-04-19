package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardAware;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.ReportSelectionDialogEventHandler;
import net.datenwerke.rs.core.client.reportmanager.hookers.ReportCatalogOnDemandRepositoryProvider;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.utils.misc.Nullable;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class JobReportConfigurationForm extends DwContentPanel implements Validatable, WizardAware, WizardResizer {

	private ListStore<ReportDto> store;
	private Grid<ReportDto> grid;
	
	private ToggleGroup exportTypeGroup;
	private Map<Radio, ReportExporter> exporterMap;
	private List<ReportExecutionConfigDto> exporterConfig;
	
	private final ReportScheduleDefinition jobDefinition;
	
	private final ReportDocumentationUiService reportDocService;
	private final ReportExecutorUIService reportExecutorService;
	private final ReportExporterUIService reportExporterService;
	private final ReportManagerTreeLoaderDao reportLoaderDao;
	private final ReportExecutorDao reportExecutorDao;
	private final Provider<ReportSelectionDialog> reportDialogProvider;
	
	private ReportSelectionDialog reportSelector;

	private VerticalLayoutContainer verticalContainer;
	private VerticalLayoutContainer exportFormFieldWrapper = new VerticalLayoutContainer();
	
	private ReportDto report;

	@Inject
	public JobReportConfigurationForm(
			ReportExecutorUIService reportExecutorService,
			ReportDocumentationUiService reportDocService,
			ReportExporterUIService reportExporterService,
			ReportManagerTreeLoaderDao reportLoaderDao,
			ReportExecutorDao reportExecutorDao,
			Provider<ReportSelectionDialog> reportDialogProvider,
			@Assisted ReportDto report, 
			@Assisted Collection<ReportViewConfiguration> configs,
			@Nullable @Assisted ReportScheduleDefinition definition
			) {
		
		super();
		this.reportExecutorService = reportExecutorService;
		this.reportDocService = reportDocService;
		this.reportExporterService = reportExporterService;
		this.reportLoaderDao = reportLoaderDao;
		this.reportExecutorDao = reportExecutorDao;
		
		this.jobDefinition = definition;
		this.report = report;
		
		this.reportDialogProvider = reportDialogProvider;
		
		setBorders(false);
		setBodyBorder(false);
		setHeaderVisible(false);
		enableScrollContainer();

		verticalContainer = new VerticalLayoutContainer();

		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightDarkStyle();
		wrapper.setHeading(SchedulerMessages.INSTANCE.report());
		wrapper.setHeight(440);
		wrapper.setWidget(verticalContainer);
		
		ToolBar toolbar = createToolbar();
		verticalContainer.add(toolbar);
		
		initGrid();
		
		verticalContainer.add(exportFormFieldWrapper, new VerticalLayoutData(1, 270, new Margins(10, 0, 0, 0)));

		add(wrapper, new MarginData(10));

	}

	private ToolBar createToolbar() {
		ToolBar toolbar = new DwToolBar();
		
		DwTextButton addBtn = new DwTextButton(FormsMessages.INSTANCE.select(), BaseIcon.REPORT);

		addBtn.addSelectHandler( event -> showReportSelector() );
		toolbar.add(addBtn);
		
		return toolbar;
	}

	private void initReportSelectionBox() {
		reportSelector = reportDialogProvider.get();
		reportSelector.initSubmitButton();
		
		reportSelector.initRepositories(report, new ReportCatalogOnDemandRepositoryProvider.Config() {
			@Override
			public boolean includeVariants() {
				return true;
			}
			
			@Override
			public boolean showCatalog() {
				return true;
			}
		});
		
		reportSelector.setHeading(SchedulerMessages.INSTANCE.report());
		reportSelector.setHeaderIcon(BaseIcon.REPORT_ADD);
		reportSelector.setClosable(true);
		reportSelector.setOnEsc(true);
		
		reportSelector.setEventHandler(new ReportSelectionDialogEventHandler() {
			
			@Override
			public boolean handleSubmit(ReportContainerDto container) {
				if (null == container || null == container.getReport())
					return false;
				onReportSelected(container.getReport());
				reportSelector.hide();
				return true;
			}
			
			@Override
			public void handleDoubleClick(final ReportContainerDto report,
					ReportSelectionRepositoryProviderHook hooker, NativeEvent event, Object... info) {
				if (null == report || null == report.getReport())
					return;
				
				onReportSelected(report.getReport());
				reportSelector.hide();
			}
			
			@Override
			public Menu getContextMenuFor(final ReportContainerDto report,
					ReportSelectionRepositoryProviderHook hooker, final Object... info) {
				return null;
			}

		});
	}

	private void showExportForm(SelectionChangedEvent<ReportDto> selectionChangedEvent) {
		
		exportFormFieldWrapper.clear();
		
		if (null == selectionChangedEvent.getSelection() || selectionChangedEvent.getSelection().isEmpty())
			return;
		
		ReportDto report = selectionChangedEvent.getSelection().get(0);
		
		exportTypeGroup = new ToggleGroup();
		HorizontalPanel horizontalRadioPanel = null;
		VerticalPanel verticalRadioPanel = new VerticalPanel();
		
		final DwTextButton formatConfigBtn = new DwTextButton(SchedulerMessages.INSTANCE.formatConfig(), BaseIcon.COG);
		formatConfigBtn.addStyleName("rs-export-type-conf-btn");
		
		List<ReportExporter> exporters = reportExporterService.getCleanedUpAvailableExporters(report);
		exporterMap = new HashMap<>();
		boolean showConfigBtn = false;
		boolean first = true;
		Radio firstRadio = null;
		int horizontalRadioCounter = 0;
		for(final ReportExporter exporter : exporters){
			if(! exporter.canBeScheduled())
				continue;
			
			showConfigBtn |= exporter.hasConfiguration(); 
			
			String name = exporter.getExportTitle();

			final Radio radio = new Radio();
			radio.setName("exportFormat"); //$NON-NLS-1$
			radio.setData("rs_outputFormat", exporter.getOutputFormat()); //$NON-NLS-1$
			radio.setBoxLabel(name);
			
			/* select default */
			if(null == jobDefinition && first)
				radio.setValue(true);
			
			if (first)
				firstRadio = radio;
			
			radio.addValueChangeHandler( event -> {
				if(radio.getValue()){
					if(exporter.hasConfiguration())
						formatConfigBtn.enable();
					else
						formatConfigBtn.disable();
				}
			});
			
			if (0 == horizontalRadioCounter % 6 ) {
				horizontalRadioPanel = new HorizontalPanel();
				horizontalRadioPanel.addStyleName("rs-export-type-radio-group");
				verticalRadioPanel.add(horizontalRadioPanel);
			}
				
			horizontalRadioPanel.add(radio);
			exportTypeGroup.add(radio);
			exporterMap.put(radio, exporter);
			
			/* select */
			if(null != jobDefinition && exporter.getOutputFormat().equals(jobDefinition.getOutputFormat())){
				radio.setValue(true);
				exporter.configureFrom(jobDefinition.getExportConfiguration());
				exporterConfig = exporter.getConfiguration();
			}
			
			if(radio.getValue())
				formatConfigBtn.setEnabled(exporter.hasConfiguration());
			
			first = false;
			
			horizontalRadioCounter++;
		}
		
		if (null == getOutputFormat())
			firstRadio.setValue(true);
		
		/* add to form */
		FieldLabel typeLabel = new FieldLabel(verticalRadioPanel,SchedulerMessages.INSTANCE.exportType());
		typeLabel.setLabelAlign(LabelAlign.LEFT);
		exportFormFieldWrapper.add(typeLabel);
		
		/* selection listener for extra config */
		if(showConfigBtn){
			formatConfigBtn.addSelectHandler( event -> {
				Radio radio = null;
				for(HasValue<Boolean> hv : exportTypeGroup){
					if(Boolean.TRUE.equals(hv.getValue())){
						radio = (Radio) hv;
						break;
					}
				}

				if(null != radio && exporterMap.containsKey(radio)){
					final ReportExporter exporter = exporterMap.get(radio);
					exporter.displayConfiguration(report, null, false, () -> exporterConfig = exporter.getConfiguration());
				}
			});
			
			horizontalRadioPanel = new HorizontalPanel();
			verticalRadioPanel.add(horizontalRadioPanel);
			verticalRadioPanel.add(formatConfigBtn);
		}
		
	}
	
	private void initGrid() {
		/* prepare store */
		store = new ListStore<ReportDto>(new DtoIdModelKeyProvider());
		store.setAutoCommit(true);
		store.addSortInfo(new StoreSortInfo<ReportDto>(ReportDtoPA.INSTANCE.name(), SortDir.ASC));

		createGrid();
		if (null != report)
			onReportSelected(report);
		
		grid.setContextMenu(new DwMenu());
		grid.addBeforeShowContextMenuHandler( event -> grid.setContextMenu(createContextMenu()) );
		
		verticalContainer.add(exportFormFieldWrapper, new VerticalLayoutData(1, 270, new Margins(10, 0, 0, 0)));

	}

	private Menu createContextMenu() {
		Menu menu = new DwMenu();
		
		/* open */
		MenuItem openItem = new DwMenuItem(BaseMessages.INSTANCE.open(), BaseIcon.ROTATE_RIGHT);
		openItem.addSelectionHandler(event -> {
			ReportDto reportDto = grid.getSelectionModel().getSelectedItem();
			if (null == reportDto)
				return;
			reportExecutorService.executeReport(reportDto);
		});
		menu.add(openItem);
		
		menu.add(new SeparatorMenuItem());
		
		/* export */
		menu.add(createExportMenuItem());
		
		menu.add(new SeparatorMenuItem());

		/* test */
		MenuItem testItem = new DwMenuItem(BaseMessages.INSTANCE.test(), BaseIcon.REPORT);
		testItem.addSelectionHandler(event -> {
			ReportDto reportDto = grid.getSelectionModel().getSelectedItem();
			if (null == reportDto)
				return;
			
			InfoConfig infoConfig = new DefaultInfoConfig(ReportExporterMessages.INSTANCE.reportIsBeingExportedTitle(), 
						ReportExporterMessages.INSTANCE.reportIsBeingExportedMsg("TEST"));
			infoConfig.setWidth(350);
			infoConfig.setDisplay(3500);
			Info.display(infoConfig);
			
			reportDocService.openVariantTestForopen(reportDto, new ArrayList<DatasourceDefinitionDto>());
		});
		menu.add(testItem);
		
		return menu;

	}

	private MenuItem createExportMenuItem() {
		final MenuItem exportItem = new DwMenuItem(ReportExporterMessages.INSTANCE.exportReport(), BaseIcon.PLAY_CIRCLE_O);
		final Menu subMenu = new DwMenu();
		exportItem.disable();
		exportItem.setSubMenu(subMenu);
		
		
		final ReportDto reportDto = grid.getSelectionModel().getSelectedItem();
		if (null == reportDto)
			return exportItem;
		
		/* load reference to ensure we have properties */
		reportLoaderDao.loadNode(reportDto, new RsAsyncCallback<AbstractNodeDto>(){
			@Override
			public void onSuccess(AbstractNodeDto result) {
				super.onSuccess(result);
				
				if(! (result instanceof ReportDto))
					return;
				
				reportExporterService.getCleanedUpAvailableExporters((ReportDto) result).forEach(exporter -> {
					MenuItem subExportItem = new DwMenuItem(exporter.getExportTitle(), exporter.getIcon());
					subMenu.add(subExportItem);
					subExportItem.addSelectionHandler(event -> {
						exporter.displayConfiguration(reportDto, null, true, () -> {
							reportExecutorDao.loadFullReportForExecution(reportDto, new RsAsyncCallback<ReportDto>() {
								@Override
								public void onSuccess(ReportDto result) {
									exporter.export(result, null);
								}
							});
						});
					});
				});
				
				if (subMenu.getWidgetCount() > 0)
					exportItem.enable();
				
			}
			
		});
		
		return exportItem;
	}

	private void createGrid() {
		/* configure columns */
		List<ColumnConfig<ReportDto, ?>> columns = new ArrayList<ColumnConfig<ReportDto, ?>>();

		/* id column */
		ColumnConfig<ReportDto, Long> idConfig = new ColumnConfig<ReportDto, Long>(ReportDtoPA.INSTANCE.id(), 80,
				BaseMessages.INSTANCE.id());
		idConfig.setMenuDisabled(true);

		columns.add(idConfig);

		/* name column */
		ColumnConfig<ReportDto, String> nameConfig = new ColumnConfig<ReportDto, String>(ReportDtoPA.INSTANCE.name(),
				320, BaseMessages.INSTANCE.name());
		nameConfig.setMenuDisabled(true);

		columns.add(nameConfig);

		/* key column */
		ColumnConfig<ReportDto, String> keyConfig = new ColumnConfig<ReportDto, String>(ReportDtoPA.INSTANCE.key(), 188,
				BaseMessages.INSTANCE.key());
		keyConfig.setMenuDisabled(true);

		columns.add(keyConfig);

		/* create grid */
		grid = new Grid<ReportDto>(store, new ColumnModel<ReportDto>(columns));
		grid.setSelectionModel(new GridSelectionModel<ReportDto>());
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.setBorders(true);
		
		grid.getSelectionModel().addSelectionChangedHandler( this::showExportForm );
		grid.addCellDoubleClickHandler( event -> showReportSelector() );
		grid.getSelectionModel().select(report, false);

		verticalContainer.add(grid, new VerticalLayoutData(1, 270, new Margins(10, 0, 0, 0)));
	}
	
	private void showReportSelector() {
		if (null == reportSelector) 
			initReportSelectionBox();
		
		reportSelector.show();
	}

	@Override
	public boolean isValid() {
		Radio radio = null;
		for(HasValue<Boolean> hv : exportTypeGroup){
			if(Boolean.TRUE.equals(hv.getValue())){
				radio = (Radio) hv;
				break;
			}
		}
		
		if (null == store.get(0)) 
			return false;
		
		if(null != radio && exporterMap.containsKey(radio)){
			final ReportExporter exporter = exporterMap.get(radio);
			if(! exporter.isConfigured()){
				new DwAlertMessageBox(SchedulerMessages.INSTANCE.formatConfig(), SchedulerMessages.INSTANCE.formatConfigError()).show();
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}

	@Override
	public void setWizard(WizardDialog dialog) {
	}

	@Override
	public int getPageHeight() {
		return 550;
	}
	
	public ToggleGroup getExportTypeGroup() {
		return exportTypeGroup;
	}
	
	public String getOutputFormat(){
		for(HasValue<Boolean> hv : exportTypeGroup)
			if(Boolean.TRUE.equals(hv.getValue()))
				return ((Radio) hv).getData("rs_outputFormat");
		
		return null;
	}
	
	public List<ReportExecutionConfigDto> getExportConfiguration(){
		return exporterConfig;
	}

	private void onReportSelected(ReportDto report) {
		this.report = report;
		store.clear();
		store.add(report);
		grid.getSelectionModel().select(report, false);
	}
	
	
	public ReportDto getReport() {
		return report;
	}
}