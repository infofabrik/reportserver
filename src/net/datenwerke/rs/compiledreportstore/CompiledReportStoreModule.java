package net.datenwerke.rs.compiledreportstore;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CompiledReportStoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CompiledReportStoreService.class).to(CompiledReportStoreServiceImpl.class).in(Singleton.class);
		
		bind(CompiledReoprtStoreStartup.class).asEagerSingleton();
	}

}
