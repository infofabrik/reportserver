package net.datenwerke.rs.tabletemplate.client.tabletemplate.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateDao;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateUIService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.pa.TableReportTemplateDtoPA;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.locale.TableTemplateMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ReportExportToTemplateHooker extends ReportExporterImpl {

	private static final TableTemplateMessages messages = GWT.create(TableTemplateMessages.class);
	
	private static TableReportTemplateDtoPA trtPa = GWT.create(TableReportTemplateDtoPA.class);
	
	private final TableTemplateDao tableTemplateDao;
	private final TableTemplateUIService tableTemplateService;
	
	private RECTableTemplateDto configuration;
	
	@Inject
	public ReportExportToTemplateHooker(
		TableTemplateDao tableTemplateDao,
		TableTemplateUIService tableTemplateService
		){
		
		/* store objects */
		this.tableTemplateDao = tableTemplateDao;
		this.tableTemplateService = tableTemplateService;
	}
	
	@Override
	public boolean consumesConfiguration(ReportDto report) {
		return true;
	}
	
	@Override
	public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration) {
		if(null == exportConfiguration || exportConfiguration.isEmpty())
			return;
		
		exportConfiguration
			.stream()
			.filter(config -> config instanceof RECTableTemplateDto)
			.findAny()
			.ifPresent(config -> configuration = (RECTableTemplateDto) config);
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof TableReportDto && ! ((TableReportDto)report).isCubeFlag();
	}

	@Override
	public String getExportTitle() {
		return messages.exportTitle();
	}

	@Override
	public String getExportDescription() {
		return messages.exportDescription();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.TEMPLATE.toImageResource();
	}

	@Override
	public String getOutputFormat() {
		return TableTemplateUIModule.EXPORT_OUTPUT_FORMAT;
	}

	@Override
	public void displayConfiguration(ReportDto report, String executorToken,
			final boolean allowAutomaticConfig, final ConfigurationFinishedCallback callback) {
		final DwWindow window = new DwWindow();
		window.setSize(300, 400);
		window.setHeading(messages.exportTitle());
		
		final ListStore<TableReportTemplateDto> templateStore = new ListStore<>(new BasicObjectModelKeyProvider<TableReportTemplateDto>());

		templateStore.addSortInfo(new StoreSortInfo<TableReportTemplateDto>(trtPa.name(), SortDir.ASC));
		
		/* create column model */
		List<ColumnConfig<TableReportTemplateDto,?>> columns = new ArrayList<>();
		
		ColumnConfig<TableReportTemplateDto,Long> idConfig = new ColumnConfig<>(trtPa.id(), 50, messages.templateId());
		idConfig.setMenuDisabled(true);
		columns.add(idConfig);
		
		ColumnConfig<TableReportTemplateDto, TableReportTemplateDto> descriptionConfig = new ColumnConfig<TableReportTemplateDto,TableReportTemplateDto>(new IdentityValueProvider<TableReportTemplateDto>(), 200, messages.templateName());
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
		final Grid<TableReportTemplateDto> grid = new Grid<>(templateStore, new ColumnModel<>(columns));
		grid.setLoadMask(true);
		grid.getView().setEmptyText(messages.noTemplates());
		grid.getView().setAutoExpandColumn(descriptionConfig);
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		window.add(grid);
		
		final DwTextButton exportBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		exportBtn.setEnabled(null != grid.getSelectionModel().getSelectedItem());
		grid.getSelectionModel().addSelectionChangedHandler(
				event -> exportBtn.setEnabled(null != grid.getSelectionModel().getSelectedItem()));
		window.addButton(exportBtn);
		exportBtn.addSelectHandler(event -> {
			TableReportTemplateDto template = grid.getSelectionModel().getSelectedItem();
			if(null != template){
				configuration = new RECTableTemplateDto();
				configuration.setTemplateId(template.getId());
				configuration.setTemporaryId(template.getTemporaryId());

				window.hide();
				callback.success();
			}
		});
		
		tableTemplateDao.loadTemplates(report, executorToken, new RsAsyncCallback<List<TableReportTemplateDto>>(){
			@Override
			public void onSuccess(List<TableReportTemplateDto> result) {
				try{
					templateStore.addAll(result);
					
					if(null != result && result.size() == 1 && allowAutomaticConfig){
						TableReportTemplateDto template = result.get(0);
						configuration = new RECTableTemplateDto();
						configuration.setTemplateId(template.getId());
						configuration.setTemporaryId(template.getTemporaryId());
	
						window.hide();
						callback.success();
					} else {
						if(null != configuration && null != configuration.getTemplateId()){
							templateStore.getAll()
								.stream()
								.filter(m -> configuration.getTemplateId().equals(m.getId()))
								.findAny()
								.ifPresent(m -> grid.getSelectionModel().select(m, false));
						}
					}
				} catch(Exception e){
					new DwAlertMessageBox(e.getMessage(), e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		window.show();
	}
	
	@Override
	public List<ReportExecutionConfigDto> getConfiguration(){
		List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
		configs.add(configuration);
		return configs;
	}
	
	@Override
	public boolean isConfigured() {
		return null != configuration;
	}
	
	@Override
	public boolean hasConfiguration() {
		return true;
	}
	
	@Override
	public boolean wantsToBeTop(ReportDto report) {
		return consumes(report) && consumesConfiguration(report) && report instanceof ReportVariantDto && ((ReportDtoDec)report).hasReportPropertyWithName(TableTemplateUIModule.TEMPLATE_LIST_PROPERTY_NAME);
	}

}
