package net.datenwerke.rs.base.client.reportengines.jasper.ui;


import com.google.gwt.user.client.ui.Hidden;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.FileSelectionConfig;
import net.datenwerke.gf.client.fileselection.FileSelectionWidget;
import net.datenwerke.gf.client.fileselection.FileSelectorSourceImpl;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.fileselectionsource.FileUploadSource;
import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.rs.base.client.reportengines.jasper.JasperUiModule;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.locale.JasperMessages;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeNoMondrian;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.AbstractReportForm;

/**
 * 
 *
 */
public class JasperReportForm extends AbstractReportForm {

	public static final String UPLOAD_MASTER_KEY = "jasperForm_uploadMasterJRXML";
	public static final String UPLOAD_SUB_KEY = "jasperForm_uploadSuJRXML";
	public static final String SUBREPORT_TYPE = "jasperform_subreport_type";
	
	private final FileUploadUiService fileUploadService;
	private final Provider<FileSelectionWidget> selectionWidgetProvider;
	
	private final Provider<UITree> datasourceTreeProvider;
	
	private FieldLabel masterLabel;
	private FileSelectionWidget selectionWidget;
	
	private static FileUploadFilter uploadFilter = new FileUploadFilter() {
		@Override
		public String doProcess(String name, long size, String base64) {
			boolean error = null == name || ! name.toLowerCase().endsWith(".jrxml");
			return error ? JasperMessages.INSTANCE.fileMustBeJrxml() : null;
		}
	};
	
	
	@Inject
	public JasperReportForm(
		DatasourceUIService datasourceService,
		FileUploadUiService fileUploadService,
		Provider<FileSelectionWidget> selectionWidgetProvider,
		@DatasourceTreeNoMondrian Provider<UITree> datasourceTreeProvider
		){
		super(datasourceService, datasourceTreeProvider);
		
		/* store objects */
		this.fileUploadService = fileUploadService;
		this.selectionWidgetProvider = selectionWidgetProvider;
		this.datasourceTreeProvider = datasourceTreeProvider;
	}
	
	@Override
	protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper){
		super.initializeForm(form, fieldWrapper);
		
		/* report id, used for upload */
		Hidden reportIdField = new Hidden();
		reportIdField.setName(ReportDto.PROPERTY_ID);
		reportIdField.setValue(String.valueOf(((JasperReportDto)getSelectedNode()).getId()));
		fieldWrapper.add(reportIdField);
		
		JasperReportJRXMLFileDto master = ((JasperReportDto)getSelectedNode()).getMasterFile();
		
		/* file upload */
		UploadProperties uploadProperties = new UploadProperties("jasperreport", JasperUiModule.MASTER_UPLOAD_HANDLER_ID);
		uploadProperties.addMetadata(JasperUiModule.META_REPORT_ID, String.valueOf(getSelectedNode().getId()));
		uploadProperties.setFilter(uploadFilter);
		
		Component uploadComponent = fileUploadService.addCombinedUploadField(uploadProperties);

		masterLabel = new FieldLabel(uploadComponent, ReportmanagerMessages.INSTANCE.JRXMLMaster() + (null != master ? " (" + master.getName() + ")" : ""));
		fieldWrapper.add(masterLabel);
		
		/* subreports */
		selectionWidget = selectionWidgetProvider.get();
		
		/* configure uploads */
		FileUploadSource uploadSource = new FileUploadSource();
		uploadSource.setUploadFilter(uploadFilter);
		selectionWidget.addSource(uploadSource);
		
		FileSelectionConfig config = new FileSelectionConfig(JasperUiModule.SUB_REPORT_HANDLER_ID);
		config.addMetadata(JasperUiModule.META_REPORT_ID, String.valueOf(getSelectedNode().getId()));
		
		/* configure subreport handler */
		selectionWidget.addSource(new FileSelectorSourceImpl(){
			@Override
			public boolean consumes(SelectedFileWrapper value) {
				return SUBREPORT_TYPE.equals(value.getType());
			}
			@Override
			public DownloadProperties getDownloadPropertiesFor(
					SelectedFileWrapper selectedItem) {
				JasperReportJRXMLFileDto jrxml = (JasperReportJRXMLFileDto) selectedItem.getOriginalDto();
				
				DownloadProperties properties = new DownloadProperties(String.valueOf(jrxml.getId()), JasperUiModule.JRXML_DOWNLOAD_HANDLER);
				return properties;
			}
			@Override
			public String getTypeDescription(SelectedFileWrapper value) {
				return "JRXML";
			}
		});
		
		/* construct widget */
		selectionWidget.build(config);
		
		/* add existing files */
		JasperReportDto report = (JasperReportDto)getSelectedNode();
		for(JasperReportJRXMLFileDto sr : report.getSubFiles()){
			SelectedFileWrapper sfw = new SelectedFileWrapper(SUBREPORT_TYPE, sr,sr.getName());
			selectionWidget.add(sfw);
		}
				
		fieldWrapper.add(new FieldLabel(selectionWidget, ReportmanagerMessages.INSTANCE.subreports()));
		
		configureUnboundFields();
	}
	
	@Override
	protected void dataProcessed(final FormViewDataProcessedClearance clearance) {
		selectionWidget.submit(new FileSelectionWidget.SubmitHandler() {
			@Override
			public void onSuccess(FileSelectionWidget fileSelectionWidget) {
				clearance.finishUp();
			}
			@Override
			public void onFailure(FileSelectionWidget fileSelectionWidget, Throwable t) {
				clearance.onError(t);
			}
		});
	}
	
	@Override
	protected boolean isUploadForm() {
		return true;
	}
	
	@Override
	protected String getFormAction() {
		return fileUploadService.getFormAction();
	}

	@Override
	protected boolean isDisplayOptionalAdditionalConfigFieldsForDatasource() {
		return false;
	}
	
	/**
	 * Set values for the fields not included in the formbinding
	 */
	private void configureUnboundFields(){
		JasperReportJRXMLFileDto master = ((JasperReportDto)getSelectedNode()).getMasterFile();
		
		masterLabel.setText(ReportmanagerMessages.INSTANCE.JRXMLMaster() + (null != master ? " (" + master.getName() + ")" : ""));
	}
	
	@Override
	protected String getHeader() {
		return ReportmanagerMessages.INSTANCE.editJasperReport() + " ("+getSelectedNode().getId()+")";
	}
	
	@Override
	protected void addDatasourceField(Container container,
			boolean displayConfigFields) {
		datasourceFieldCreator = datasourceService.getSelectionField(container, displayConfigFields, datasourceTreeProvider);
		datasourceFieldCreator.setShouldShowDefaultAdditionalConfig(true);
		datasourceFieldCreator.addSelectionField();
		datasourceFieldCreator.addDisplayDefaultButton();
	}


}
