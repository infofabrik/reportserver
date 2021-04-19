package net.datenwerke.rs.uservariables.service.stump;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableServiceImpl;
import net.datenwerke.rs.uservariables.service.uservariables.annotations.UserVariableTypes;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableProviderHook;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class UserVariableStumpModule extends AbstractModule{

	@Override
	protected void configure() {
		/* bind service */
		bind(UserVariableService.class).to(UserVariableServiceImpl.class).in(Scopes.SINGLETON);

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
