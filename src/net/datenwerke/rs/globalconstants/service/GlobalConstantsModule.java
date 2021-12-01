package net.datenwerke.rs.globalconstants.service;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.globalconstants.service.globalconstants.GlobalConstantsService;
import net.datenwerke.rs.globalconstants.service.globalconstants.GlobalConstantsServiceImpl;
import net.datenwerke.rs.globalconstants.service.globalconstants.genrights.GenRightsGlobalConstantsManagerModule;

public class GlobalConstantsModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(GlobalConstantsService.class).to(GlobalConstantsServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(GlobalConstantsStartup.class).asEagerSingleton();
		
		/* sub modules */
		install(new GenRightsGlobalConstantsManagerModule());
	}

}
