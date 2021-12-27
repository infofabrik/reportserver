package net.datenwerke.rs.tabletemplate.client.tabletemplate;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class TableTemplateUIModule extends AbstractGinModule {

	public static final String DOWNLOAD_SERVLET = "TableTemplateDownload";
	public static final String FORM_ACTION = "TableTemplateUpload";
	public static final String UPLOAD_FIELD_NAME = "xlsTemplate";
	public static final String EXECUTE_REPORT_TOKEN = "executeReportToken";
	
	public static final String EXPORT_OUTPUT_FORMAT = "TABLE_TEMPLATE";
	public static final String REPORT_ID = "reportId";
	public static final String TEMPLATE_ID = "templateId";
	public static final String TEMPLATE_TEMPORARY_ID = "templateTemporaryId";
	
	public static final String TEMPLATE_LIST_PROPERTY_NAME = "tabletemplate:templatelist";
	
	@Override
	protected void configure() {
		bind(TableTemplateUIService.class).to(TableTemplateUIServiceImpl.class).in(Singleton.class);
		bind(TableTemplateUIStartup.class).asEagerSingleton();
	}

}
