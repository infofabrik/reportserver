package net.datenwerke.rs.tabletemplate.client.tabletemplate.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Hidden;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwFileUploadField;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks.FilterViewEnhanceToolbarHook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets.FilterView;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateDao;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateUIService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.pa.TableReportTemplateDtoPA;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook.ContentTypeConfig;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.locale.TableTemplateMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class EnhanceFilterToolbarHooker implements FilterViewEnhanceToolbarHook {
	
	class ModelClass {
		private String name;
		private String description;
		private ImageResource icon;
		private String type;
		private TableTemplateClientProviderHook provider;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public SafeHtml getIcon() {
			return AbstractImagePrototype.create(icon).getSafeHtml();
		}
		public void setIcon(ImageResource icon) {
			this.icon = icon;
		}
		public void setIcon(SafeHtml icon) {
			
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public TableTemplateClientProviderHook getProvider() {
			return provider;
		}
		public void setProvider(TableTemplateClientProviderHook provider) {
			this.provider = provider;
		}
	}
	
	@FormatterFactories(@FormatterFactory(factory=NullSafeFormatter.class,methods=@FormatterFactoryMethod(name="nullsafe")))
	public interface TableTemplateTemplates extends XTemplates {
		@XTemplate("<div class=\"teamspace-nav-item x-combo-list-item\">" + 
					    "<div class=\"teamspace-nav-image\">{icon}</div>" + 
					    "<div class=\"teamspace-nav-title\">{name:nullsafe}</div>" + 
					    "<div class=\"teamspace-nav-description\">{description:nullsafe}</div>" + 
				    "</div>")
	    public SafeHtml render(ModelClass entry); 
	}
	
	private static final TableTemplateMessages messages = GWT.create(TableTemplateMessages.class);
	
	private static TableReportTemplateDtoPA trtPa = GWT.create(TableReportTemplateDtoPA.class);
	
	private final HookHandlerService hookHandler;
	private final TableTemplateDao tableTemplateDao;
	private final TableTemplateUIService tableTemplateService;
	private final ToolbarService toolbarService;
	private final EnterpriseUiService enterpriseService;

	private Grid<TableReportTemplateDto> grid;
	private DwContentPanel editTemplateComponent;

	private ListStore<TableReportTemplateDto> templateStore;

	private FilterView filterView;

	private TableReportDto report;

	private DwWindow window;

	private VerticalLayoutContainer wrapper;

	private DwBorderContainer borderWrapper;
	
	private interface SubmitCompleteCallback {

		public void onSuccess();
		public void onFailure(String msg);
	}


	@Inject
	public EnhanceFilterToolbarHooker(
		HookHandlerService hookHandler,
		TableTemplateDao tableTemplateDao,
		TableTemplateUIService tableTemplateService,
		ToolbarService toolbarService,
		EnterpriseUiService enterpriseService
		){
		
		/* store obejcts */
		this.hookHandler = hookHandler;
		this.tableTemplateDao = tableTemplateDao;
		this.tableTemplateService = tableTemplateService;
		this.toolbarService = toolbarService;
		this.enterpriseService = enterpriseService;
	}
	
	@Override
	public void initialize(FilterView filterView, TableReportDto report) {
		this.filterView = filterView;
		this.report = report;
	}
	
	@Override
	public void enhanceToolbarLeft(ToolBar toolbar) {
		if(! enterpriseService.isEnterprise())
			return;
		
		DwTextButton editTemplateBtn = toolbarService.createSmallButtonLeft(messages.editTemplateBtn(), BaseIcon.TEMPLATE);
		editTemplateBtn.addSelectHandler(event -> displayEditTemplateDialog());

		toolbar.add(editTemplateBtn);
	}


	protected void displayEditTemplateDialog() {
		window = new DwWindow();
		window.setSize(640, 640);
		window.setHeading(messages.editTemplateBtn());
		window.setHeaderIcon(BaseIcon.TEMPLATE);
		window.setBodyBorder(true);
		window.setModal(true);

		/* create wrapper with border layout */
		wrapper = new VerticalLayoutContainer();
		window.add(wrapper);
		
		/* create store */
		templateStore = new ListStore<>(new BasicObjectModelKeyProvider<>());
		templateStore.setAutoCommit(true);
		
		/* create components */
		grid = createGrid();
		editTemplateComponent = createEditTemplateComponent();
		
		/* grid wrapper */
		DwContentPanel gridWrapper = DwContentPanel.newInlineInstance(grid);
		
		/* add toolbar */
		ToolBar toolbar = createToolbar();
		wrapper.add(toolbar, new VerticalLayoutData(1, -1));
		
		/* add border container */
		borderWrapper = new DwBorderContainer();
		wrapper.add(borderWrapper, new VerticalLayoutData(1,1));
		
		/* add compoenents */
		BorderLayoutData westData = new BorderLayoutData(320);
		
		borderWrapper.setWestWidget(grid, westData);
		borderWrapper.setCenterWidget(editTemplateComponent);
		
		/* close button */
		DwTextButton closeBtn = new DwTextButton(BaseMessages.INSTANCE.close());
		window.addButton(closeBtn);
		closeBtn.addSelectHandler(event -> window.hide());
		
		/* load templates */
		loadTemplates();
		
		window.show();
	}
	
	private void loadTemplates() {
		wrapper.mask(BaseMessages.INSTANCE.loadingMsg());
		tableTemplateDao.loadTemplates(report, filterView.getExecuteReportToken(), new RsAsyncCallback<List<TableReportTemplateDto>>(){
			@Override
			public void onSuccess(List<TableReportTemplateDto> result) {
				templateStore.addAll(result);
				templateStore.addSortInfo(new StoreSortInfo<TableReportTemplateDto>(trtPa.name(), SortDir.ASC));
				wrapper.unmask();
			}
		});
	}

	private ToolBar createToolbar() {
		ToolBar toolbar = new DwToolBar();
		
		DwTextButton addTemplateBtn = toolbarService.createSmallButtonLeft(messages.addTemplateBtn(), BaseIcon.TEMPLATE);
		addTemplateBtn.addSelectHandler(event -> displayAddTemplateDialog());
		toolbar.add(addTemplateBtn);
		
		toolbar.add(new SeparatorToolItem());
		
		DwTextButton removeTemplatesBtn = toolbarService.createSmallButtonLeft(messages.removeTemplateBtn(), BaseIcon.DELETE);
		removeTemplatesBtn.addSelectHandler(event -> {
			TableReportTemplateDto template = grid.getSelectionModel().getSelectedItem();
			if(null != template){
				List<TableReportTemplateDto> toRemove = new ArrayList<>();
				toRemove.add(template);
				
				tableTemplateDao.removeTemplates(report, filterView.getExecuteReportToken(), toRemove, new RsAsyncCallback<Void>(){
					@Override
					public void onSuccess(Void result) {
						reloadTemplates();
					}
				});
			}
		});
		
		toolbar.add(removeTemplatesBtn);
		
		toolbar.add(new FillToolItem());
		
		DwTextButton downloadTemplatesBtn = toolbarService.createSmallButtonLeft(messages.downloadTemplateBtn(), BaseIcon.FLOPPY_O);
		downloadTemplatesBtn.addSelectHandler(event -> downloadTemplate());
		toolbar.add(downloadTemplatesBtn);
		
		return toolbar;
	}

	protected void downloadTemplate() {
		TableReportTemplateDto template = grid.getSelectionModel().getSelectedItem();
		if(null != template){
			String url = GWT.getModuleBaseURL() + 
				TableTemplateUIModule.DOWNLOAD_SERVLET + "?" + 
				TableTemplateUIModule.EXECUTE_REPORT_TOKEN + "=" + filterView.getExecuteReportToken() + 
				"&" + TableTemplateUIModule.REPORT_ID + "=" + report.getId() + 
				"&" + TableTemplateUIModule.TEMPLATE_ID + "=" + template.getId() +
				"&" + TableTemplateUIModule.TEMPLATE_TEMPORARY_ID + "=" + template.getTemporaryId();

			ClientDownloadHelper.triggerDownload(url);
		}
	}

	protected void displayAddTemplateDialog() {
		final DwWindow window = new DwWindow();
		window.setSize(350, 540);
		window.setHeading(messages.addTemplateBtn());
		
		DwFlowContainer wrapper = new DwFlowContainer();
		wrapper.setScrollMode(ScrollMode.AUTOY);
		window.add(wrapper, new MarginData(5));
		
		final FormPanel form = createEditForm(new SubmitCompleteCallback() {
			@Override
			public void onSuccess() {
				window.hide();
				
				/* remove all and reload templates */
				reloadTemplates();
			}
			
			@Override
			public void onFailure(String msg) {
				displayErrorMessages(msg);
			}
		}, null);
		wrapper.add(form, new MarginData(5));
		
		window.addCancelButton();
		
		DwTextButton submit = new DwTextButton(BaseMessages.INSTANCE.submit());
		submit.addSelectHandler(event -> {
			form.mask(BaseMessages.INSTANCE.storingMsg());
			form.submit();
		});
		window.addButton(submit);
		
		window.show();
	}

	protected void reloadTemplates() {
		templateStore.clear();
		loadTemplates();
		editTemplateComponent.clear();
	}

	private DwContentPanel createEditTemplateComponent() {
		DwContentPanel panel = DwContentPanel.newInlineInstance();
		panel.addClassName("rs-dl-template-edit");
		
		return panel;
	}

	private Grid<TableReportTemplateDto> createGrid() {
		/* create column model */
		List<ColumnConfig<TableReportTemplateDto,?>> columns = new ArrayList<>();
		
		ColumnConfig<TableReportTemplateDto,Long> idConfig = new ColumnConfig<>(trtPa.id(), 50, messages.templateId());
		idConfig.setMenuDisabled(true);
		columns.add(idConfig);
		
		ColumnConfig<TableReportTemplateDto,TableReportTemplateDto> descriptionConfig = new ColumnConfig<TableReportTemplateDto,TableReportTemplateDto>(new IdentityValueProvider<TableReportTemplateDto>(), 250, messages.templateName());
		descriptionConfig.setCell(new AbstractCell<TableReportTemplateDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					TableReportTemplateDto value, SafeHtmlBuilder sb) {
				sb.append(tableTemplateService.getDescriptionTemplate().descriptionTemplate(value));
			}
		});

		descriptionConfig.setMenuDisabled(true);
		columns.add(descriptionConfig);
		
		/* create grid */
		Grid<TableReportTemplateDto> grid = new Grid<>(templateStore, new ColumnModel<>(columns));
		grid.setLoadMask(true);
		grid.getView().setAutoExpandColumn(descriptionConfig);
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.getSelectionModel().addSelectionChangedHandler(event ->  {
			if(null != event.getSelection() && ! event.getSelection().isEmpty()){
				TableReportTemplateDto template = event.getSelection().get(0);
				if(null != template)
					displayEditTemplate(template);
			}
		});
		
		return grid;
	}

	protected void displayEditTemplate(TableReportTemplateDto template) {
		editTemplateComponent.clear();
		
		final FormPanel form = createEditForm(new SubmitCompleteCallback() {
			
			@Override
			public void onSuccess() {
				reloadTemplates();
			}
			
			@Override
			public void onFailure(String msg) {
				displayErrorMessages(msg);
			}
		}, template);
		
		
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit(), event -> {
			form.mask(BaseMessages.INSTANCE.storingMsg());
			form.submit();	
		});
		
		DwFlowContainer wrapper = new DwFlowContainer();
		wrapper.setScrollMode(ScrollMode.AUTOY);
		wrapper.add(form);
		
		editTemplateComponent.add(wrapper, new MarginData(10));
		
		ButtonBar bar = new ButtonBar();
		bar.add(new FillToolItem());
		bar.add(submitBtn);
		
		((VerticalLayoutContainer)form.getWidget()).add(bar);
		
		editTemplateComponent.forceLayout();
		
		editTemplateComponent.getButtonBar().forceLayout();
	}

	protected FormPanel createEditForm(final SubmitCompleteCallback callback, final TableReportTemplateDto template){
		final FormPanel form = new FormPanel();

		form.setAction(GWT.getModuleBaseURL() + TableTemplateUIModule.FORM_ACTION);
		form.setMethod(Method.POST);
		form.setEncoding(Encoding.MULTIPART);
		form.setLabelAlign(LabelAlign.TOP);
		
		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
		form.setWidget(layoutContainer);
		
		Hidden reportIdField = new Hidden();
		reportIdField.setName(ReportDto.PROPERTY_ID); 
		reportIdField.setValue(String.valueOf(report.getId()));
		layoutContainer.add(reportIdField);
		
		Hidden templateIdField = new Hidden();
		templateIdField.setName(TableReportTemplateDto.PROPERTY_ID); 
		if(null != template)
			templateIdField.setValue(null == template.getId() ? "" : String.valueOf(template.getId()));
		layoutContainer.add(templateIdField);
		
		Hidden templateTempIdField = new Hidden();
		templateTempIdField.setName(TableReportTemplateDto.PROPERTY_TEMPORARY_ID); 
		if(null != template)
			templateTempIdField.setValue(null == template.getTemporaryId() ? "" : String.valueOf(template.getTemporaryId()));
		layoutContainer.add(templateTempIdField);
		
		Hidden reportExecuteTokenField = new Hidden();
		reportExecuteTokenField.setName(TableTemplateUIModule.EXECUTE_REPORT_TOKEN);
		reportExecuteTokenField.setValue(filterView.getExecuteReportToken());
		layoutContainer.add(reportExecuteTokenField);
		
		TextField nameField = new TextField();
		nameField.setWidth(-1);
		nameField.setName(TableReportTemplateDto.PROPERTY_NAME);
		if(null != template)
			nameField.setValue(template.getName());
		layoutContainer.add(new FieldLabel(nameField, BaseMessages.INSTANCE.propertyName()), new VerticalLayoutData(1,-1));

		TextArea descriptionField = new TextArea();
		descriptionField.setWidth(-1);
		descriptionField.setHeight(100);
		descriptionField.setName(TableReportTemplateDto.PROPERTY_DESCRIPTION);
		if(null != template)
			descriptionField.setValue(template.getDescription());
		layoutContainer.add(new FieldLabel(descriptionField, BaseMessages.INSTANCE.propertyDescription()), new VerticalLayoutData(1,-1));
		
		TextField keyField = new TextField();
		keyField.setWidth(-1);
		keyField.setName(TableReportTemplateDto.PROPERTY_KEY);
		if(null != template)
			keyField.setValue(template.getKey());
		layoutContainer.add(new FieldLabel(keyField, messages.templateKeyLabel()), new VerticalLayoutData(1,-1));
		
		final ListStore<ModelClass> typeStore = new ListStore<>(new BasicObjectModelKeyProvider<>());
		
		hookHandler.getHookers(TableTemplateClientProviderHook.class)
			.forEach(provider -> {
				ModelClass type = new ModelClass();
	
				type.setName(provider.getName());
				type.setDescription(provider.getDescription());
				type.setIcon(provider.getIconLarge());
				type.setType(provider.getType());
				type.setProvider(provider);
	
				typeStore.add(type);
		});
		
		final TableTemplateTemplates xtemplate = GWT.create(TableTemplateTemplates.class);
		
		ListView<ModelClass, ModelClass> view = new ListView<ModelClass, ModelClass>(typeStore, new IdentityValueProvider<ModelClass>());
		view.setCell(new AbstractCell<ModelClass>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ModelClass value, SafeHtmlBuilder sb) {
				sb.append(xtemplate.render(value));
			}
		});
		
		final ComboBox<ModelClass> templateTypeCombo = new ComboBox<>(new ComboBoxCell<>(typeStore, 
				ModelClass::getName, view));
		templateTypeCombo.setWidth(-1);
		templateTypeCombo.setAllowTextSelection(false);
		templateTypeCombo.setStore(typeStore);
		templateTypeCombo.setMinListWidth(500);
		templateTypeCombo.setTriggerAction(TriggerAction.ALL);
		templateTypeCombo.setForceSelection(true);
		if(null == template){
			templateTypeCombo.setValue(typeStore.get(0));
		} else {
			typeStore.getAll()
				.stream()
				.filter(model -> model.getType().equals(template.getTemplateType()))
				.findAny()
				.ifPresent(model -> templateTypeCombo.setValue(model));
		}
		templateTypeCombo.setAllowBlank(false);
		templateTypeCombo.setWidth(-1);
		layoutContainer.add(new FieldLabel(templateTypeCombo, messages.typeFieldLabel()), new VerticalLayoutData(1,-1));
		
		/* hidden field for template */
		final Hidden hiddenTemplateTypeField = new Hidden();
		hiddenTemplateTypeField.setName(TableReportTemplateDto.PROPERTY_TEMPLATE_TYPE);
		if(null != templateTypeCombo.getValue())
			hiddenTemplateTypeField.setValue(templateTypeCombo.getValue().getType());
		layoutContainer.add(hiddenTemplateTypeField);
		
		FileUploadField file = new DwFileUploadField();
		file.setName(TableTemplateUIModule.UPLOAD_FIELD_NAME);
		file.setWidth(-1);
		layoutContainer.add(new FieldLabel(file, messages.uploadFieldLabel()), new VerticalLayoutData(1,-1));
		
		final TextField contentTypeField = new TextField();
		contentTypeField.setWidth(-1);
		contentTypeField.setName(TableReportTemplateDto.PROPERTY_CONTENT_TYPE);
		if(null != template)
			contentTypeField.setValue(template.getContentType());
		layoutContainer.add(new FieldLabel(contentTypeField, messages.templateContentTypeLabel()), new VerticalLayoutData(1,-1));
		
		final TextField fileExtensionField = new TextField();
		fileExtensionField.setWidth(-1);
		fileExtensionField.setName(TableReportTemplateDto.PROPERTY_FILE_EXTENSION);
		if(null != template)
			fileExtensionField.setValue(template.getFileExtension());
		layoutContainer.add(new FieldLabel(fileExtensionField,messages.fileExtensionLabel()), new VerticalLayoutData(1,-1));
		
		/* update fields according to selected type */
		ModelClass templateType = templateTypeCombo.getValue();
		if(null == templateType){
			contentTypeField.disable();
			fileExtensionField.disable();
		} else {
			TableTemplateClientProviderHook provider = templateType.getProvider();
			ContentTypeConfig config = provider.getContentTypeConfig();
			if(config.isDisplay()){
				contentTypeField.enable();
				if(null == contentTypeField.getValue())
					contentTypeField.setValue(config.getDefaultContentType());
				
				fileExtensionField.enable();
				if(null == fileExtensionField.getValue())
					fileExtensionField.setValue(config.getDefaultFileExtension());
			} else {
				contentTypeField.disable();
				fileExtensionField.disable();
			}
		}
				
		/* update on change */
		templateTypeCombo.addSelectionHandler(new SelectionHandler<EnhanceFilterToolbarHooker.ModelClass>() {
			@Override
			public void onSelection(SelectionEvent<ModelClass> event) {
				ModelClass templateType = templateTypeCombo.getCurrentValue();
				if(null == templateType){
					contentTypeField.disable();
					fileExtensionField.disable();
					hiddenTemplateTypeField.setValue(null);
					return;
				} else {
					hiddenTemplateTypeField.setValue(templateType.getType());
					
					TableTemplateClientProviderHook provider = templateType.getProvider();
					ContentTypeConfig config = provider.getContentTypeConfig();
					if(config.isDisplay()){
						contentTypeField.enable();
						contentTypeField.setValue(config.getDefaultContentType());
						
						fileExtensionField.enable();
						fileExtensionField.setValue(config.getDefaultFileExtension());
					} else {
						contentTypeField.disable();
						fileExtensionField.disable();
					}
				}
			}
		});
		
		form.addSubmitCompleteHandler(event -> {
			form.unmask();
			if(!event.getResults().contains(FileUploadUIModule.UPLOAD_SUCCESSFUL_PREFIX)) //$NON-NLS-1$
				callback.onFailure(event.getResults());
			else 
				callback.onSuccess();
		});
		
		return form;
	}
	
	private void displayErrorMessages(String msg){
		DetailErrorDialog dialog = new DetailErrorDialog(BaseMessages.INSTANCE.error(),  messages.upoadError(), msg);
		dialog.show();
	}
	
	
	@Override
	public void enhanceToolbarRight(ToolBar toolbar) {

	}

}
