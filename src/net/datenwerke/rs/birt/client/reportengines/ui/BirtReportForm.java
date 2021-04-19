package net.datenwerke.rs.birt.client.reportengines.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.rs.birt.client.reportengines.BirtUiModule;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;
import net.datenwerke.rs.birt.client.reportengines.locale.BirtMessages;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeNoMondrian;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.AbstractReportForm;

public class BirtReportForm extends AbstractReportForm {

	private final FileUploadUiService fileUploadService;
	
	private final Provider<UITree> datasourceTreeProvider;
	
	private Label uploadLabel;

	private static FileUploadFilter uploadFilter = new FileUploadFilter() {
		@Override
		public String doProcess(String name, long size, String base64) {
			boolean error = null == name || ! name.toLowerCase().endsWith(".rptdesign");
			return error ? BirtMessages.INSTANCE.fileMustBeRptDesign() : null;
		}
	};
	
	@Inject
	public BirtReportForm(
		DatasourceUIService datasourceService,
		FileUploadUiService fileUploadService,
		@DatasourceTreeNoMondrian Provider<UITree> datasourceTreeProvider
	) {
		super(datasourceService, datasourceTreeProvider);
		
		/* store objects */
		this.fileUploadService = fileUploadService;
		this.datasourceTreeProvider = datasourceTreeProvider;
	}
	
	@Override
	protected void initializeForm(FormPanel form,
			VerticalLayoutContainer fieldWrapper) {
		super.initializeForm(form, fieldWrapper);
		
		UploadProperties uploadProperties = new UploadProperties("birtreport", BirtUiModule.UPLOAD_HANDLER_ID);
		uploadProperties.addMetadata(BirtUiModule.UPLOAD_REPORT_ID_FIELD, String.valueOf(getSelectedNode().getId()));
		uploadProperties.setFilter(uploadFilter);
		
		Component uploadComponent = fileUploadService.addCombinedUploadField(uploadProperties);
		fieldWrapper.add(new FieldLabel(uploadComponent, BirtMessages.INSTANCE.rptdesign()));

		uploadLabel = new Label(BirtMessages.INSTANCE.fileName());
		BirtReportFileDto reportfile = ((BirtReportDto)getSelectedNode()).getReportFile();
		if(null != reportfile)
			uploadLabel.setText(reportfile.getName());
		fieldWrapper.add(uploadLabel);
		
		configureUnboundFields();
	}
	
	@Override
	protected String getHeader() {
		return BirtMessages.INSTANCE.editBirtReport()  + " ("+getSelectedNode().getId()+")"; //$NON-NLS-1$ //$NON-NLS-2$
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

	@Override
	protected void configureAutoBinding(FormBinding binding) {
//		binding.addIgnorePathsForAutoBinding(FileUploadUIModule.FIELDNAME_UPLOAD_ID, FileUploadUIModule.UPLOAD_FILE_PREFIX);
	}
	
	
	/**
	 * Set values for the fields not included in the formbinding
	 */
	private void configureUnboundFields(){
		BirtReportFileDto file = ((BirtReportDto)getSelectedNode()).getReportFile();
		if(null != file)
			uploadLabel.setText(file.getName());

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
