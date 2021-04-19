package net.datenwerke.rs.eximport.client.eximport;

import net.datenwerke.rs.eximport.client.eximport.im.ImportUIModule;

import com.google.gwt.inject.client.AbstractGinModule;

public class RsExImportUiModule extends AbstractGinModule {

	public static final String IMPORT_DATA_FORM_ACTION = "importDataUploadServlet";
	public static final String IMPORT_DATA_FILE_NAME = "importDataFile";
	public static final String IMPORT_IGNORE_WARNING_KEY = "ignoreWarnings";
	public static final String IMPORT_IGNORE_ERRORS_KEY = "ignoreErrors";
	
	public static final String UPLOAD_HANDLER_ID = "imexport_upload_handler";

	@Override
	protected void configure() {
		bind(ExImportStartup.class).asEagerSingleton();
		
		install(new ImportUIModule());
	}

}
