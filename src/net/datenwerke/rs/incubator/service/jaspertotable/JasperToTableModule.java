package net.datenwerke.rs.incubator.service.jaspertotable;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

import com.google.inject.Singleton;

public class JasperToTableModule extends AbstractReportServerModule {

	public static final String PROPERTY_NAME = "jaspertotable:config";
	
	@Override
	protected void configure() {
		bind(JasperToTableService.class).to(JasperToTableServiceImpl.class).in(Singleton.class);
		
		bind(JasperToTableStartup.class).asEagerSingleton();
	}

}
