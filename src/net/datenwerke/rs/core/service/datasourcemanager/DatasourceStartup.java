package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.eventhandlers.HandleDatasourceForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasourcemanager.history.DatasourceManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DatasourceStartup {

	@Inject
	public DatasourceStartup(
		HookHandlerService hookHandler,
		EventBus eventBus,
		final Provider<SecurityService> securityServiceProvider,
		final @ReportServerDatasourceDefinitions Provider<Set<Class<? extends DatasourceDefinition>>> installedDataSourceDefinitions,
		
		Provider<DatasourceManagerHistoryUrlBuilderHooker> datasourceManagerUrlBuilder,
		HandleDatasourceForceRemoveEventHandler handleDatasourceForceRemoveHandler
		){
		
		eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasourceDefinition.class, handleDatasourceForceRemoveHandler);
		
		/* history */
		hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasourceManagerUrlBuilder);
		
		/* register security targets */
		hookHandler.attachHooker(ConfigDoneHook.class, new ConfigDoneHook() {
			
			@Override
			public void configDone() {
				/* secure folder */
				securityServiceProvider.get().registerSecurityTarget(DatasourceFolder.class);

				
				/* secure datasource definition entities */
				for(Class<? extends DatasourceDefinition> dClass : installedDataSourceDefinitions.get())
					securityServiceProvider.get().registerSecurityTarget(dClass);
			}
		});
	}
}
