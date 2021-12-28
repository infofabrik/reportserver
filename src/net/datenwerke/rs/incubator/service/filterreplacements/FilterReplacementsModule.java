package net.datenwerke.rs.incubator.service.filterreplacements;

import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.incubator.service.filterreplacements.agg.AggWrapperFactory;
import net.datenwerke.rs.incubator.service.filterreplacements.analytics.AnalyticalFunctionWrapperFactory;

public class FilterReplacementsModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(FilterReplacementsStartup.class).asEagerSingleton();
		
		/* create factory */
		install(new FactoryModuleBuilder().build(AggWrapperFactory.class));
		install(new FactoryModuleBuilder().build(AnalyticalFunctionWrapperFactory.class));
	}

}
