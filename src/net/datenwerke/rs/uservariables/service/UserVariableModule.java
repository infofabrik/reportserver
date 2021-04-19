package net.datenwerke.rs.uservariables.service;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.uservariables.service.genrights.ClientUserVariableModule;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableServiceImpl;
import net.datenwerke.rs.uservariables.service.uservariables.annotations.UserVariableTypes;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableProviderHook;

public class UserVariableModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(UserVariableService.class).to(UserVariableServiceImpl.class).in(Scopes.SINGLETON);
		
		/* bind startup */
		bind(UserVariableStartup.class).asEagerSingleton();
		
		install(new ClientUserVariableModule());
		
		requestStaticInjection(			// should not be here
			UserVariableParameterInstance.class
		);
	}

	
	@Provides @UserVariableTypes @Inject
	public Set<UserVariableDefinition> provideUserVariableTypes(HookHandlerService hookHandler){
		Set<UserVariableDefinition> definitions = new HashSet<UserVariableDefinition>();
		
		for(UserVariableProviderHook hooker : hookHandler.getHookers(UserVariableProviderHook.class))
			definitions.addAll(hooker.getVariables());
		
		return definitions;
	}
}
