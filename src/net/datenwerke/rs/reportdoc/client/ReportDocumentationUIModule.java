package net.datenwerke.rs.reportdoc.client;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 *
 */
public class ReportDocumentationUIModule extends AbstractGinModule {

	public static final String SERVLET = "reportdocumentation";
	
	public static final String LEFT_REPORT_PROPERTY_ID = "leftRepId";
	public static final String RIGHT_REPORT_PROPERTY_ID = "rightRepId";
	public static final String IGNORE_CASE_PROPERTY_ID = "ignoreCase";
	
	public static final String REPORT_PROPERTY_ID = "reportId";
	public static final String DATASOURCE_PROPERTY_ID = "datasource";
	
	@Override
	protected void configure() {
		/* startup */
		bind(ReportDocumentationUIStartup.class).asEagerSingleton();
		
	}

}
