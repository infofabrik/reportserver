package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.core.service.datasinkmanager.eventhandlers.HandleDatasinkForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasinkmanager.history.DatasinkManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

public class DatasinkStartup {

	@Inject
	public DatasinkStartup(
		HookHandlerService hookHandler,
		EventBus eventBus,
		final Provider<SecurityService> securityServiceProvider,
		final @ReportServerDatasinkDefinitions Provider<Set<Class<? extends DatasinkDefinition>>> installedDataSinkDefinitions,
		
		Provider<DatasinkManagerHistoryUrlBuilderHooker> datasinkManagerUrlBuilder,
		HandleDatasinkForceRemoveEventHandler handleDatasinkForceRemoveHandler
		){
		
		eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasinkDefinition.class, handleDatasinkForceRemoveHandler);
		
		/* history */
		hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasinkManagerUrlBuilder);
		
		/* register security targets */
		hookHandler.attachHooker(ConfigDoneHook.class, new ConfigDoneHook() {
			
			@Override
			public void configDone() {
				/* secure folder */
				securityServiceProvider.get().registerSecurityTarget(DatasinkFolder.class);

				
				/* secure datasink definition entities */
				for(Class<? extends DatasinkDefinition> dClass : installedDataSinkDefinitions.get())
					securityServiceProvider.get().registerSecurityTarget(dClass);
			}
		});
	}
}
