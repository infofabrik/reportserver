package net.datenwerke.rs.base.service.parameters;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.parameters.eventhandlers.HandleDatasourceRemoveEventHandler;
import net.datenwerke.rs.base.service.parameters.hookers.BaseParameterProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterProviderHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseParametersStartup {

	@Inject
	public BaseParametersStartup(
		HookHandlerService hookHandler,
		EventBus eventBus,
		
		Provider<BaseParameterProviderHooker> parameterProvider,
		
		HandleDatasourceRemoveEventHandler handleDatasourceRemoveEventHandler
		
		){
		
		hookHandler.attachHooker(ParameterProviderHook.class, parameterProvider);
		
		eventBus.attachObjectEventHandler(RemoveEntityEvent.class, DatasourceDefinition.class, handleDatasourceRemoveEventHandler);
		
	}
}
