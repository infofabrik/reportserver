package net.datenwerke.rs.core.service.datasourcemanager.stump;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceServiceImpl;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceStartup;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.DefaultDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class DatasourceStumpModule extends AbstractReportServerModule {

		
	@Override
	protected void configure() {
		bind(DatasourceService.class).to(DatasourceServiceImpl.class).in(Scopes.SINGLETON);
		
		/* startup */
		bind(DatasourceStartup.class).asEagerSingleton();
	}


	/**
	 * Register DatasourceDefinitions
	 * 
	 */
	@Provides @ReportServerDatasourceDefinitions @Inject
	public Set<Class<? extends DatasourceDefinition>> provideDataSourceDefinitions(
		HookHandlerService hookHandler	
		){
		Set<Class<? extends DatasourceDefinition>> definitions = new HashSet<Class<? extends DatasourceDefinition>>();
		
		for(DatasourceProviderHook dsProvider : hookHandler.getHookers(DatasourceProviderHook.class))
			definitions.addAll(dsProvider.getDatasources());
		
		return definitions;
	}
	
	@Provides @DefaultDatasource
	public String provideDefaultDatasourceId(){
		return null;
	}
}
