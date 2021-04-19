package net.datenwerke.rs.scheduler.client.scheduler;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.ExportConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerMetadataConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerReportConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanelFactory;

/**
 * 
 *
 */
public class SchedulerUIModule extends AbstractGinModule {

	public static final String USER_PROPERTY_VIEW_VERTICAL_SPLIT = "reportScheduler:view:split";
	
	public static final String PROPERTY_CONTAINER = "SchedulerService_PropertyContainer"; //$NON-NLS-1$
	public static final String PROPERTY_EMAIL_SUBJECT = "scheduler:email:subject";
	public static final String PROPERTY_EMAIL_TEXT = "scheduler:email:text";
	public static final String PROPERTY_EMAIL_ATTACHEMENT_NAME = "scheduler:email:attachment:name";

	
	@Override
	protected void configure() {
		bind(SchedulerUiService.class).to(SchedulerUiServiceImpl.class).in(Singleton.class);
		
		/* create factory */
		install(new GinFactoryModuleBuilder().build(SchedulerMetadataConfigFormFactory.class));
		install(new GinFactoryModuleBuilder().build(SchedulerReportConfigFormFactory.class));
		install(new GinFactoryModuleBuilder().build(ExportConfigFormFactory.class));
		install(new GinFactoryModuleBuilder().build(SeriesConfigFormFactory.class));
		install(new GinFactoryModuleBuilder().build(ScheduledReportListPanelFactory.class));
		
		
		/* startup */
		bind(SchedulerUIStartup.class).asEagerSingleton();
	}


}
