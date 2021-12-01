package net.datenwerke.rs.uservariables.service.parameters.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterProviderHook;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;

public class UserVariableParameterProviderHooker implements ParameterProviderHook {

	@Override
	public Collection<? extends Class<? extends ParameterDefinition>> getParameterDefinitions() {
		Set<Class<? extends ParameterDefinition>> definitions = new HashSet<Class<? extends ParameterDefinition>>();
		
		definitions.add(UserVariableParameterDefinition.class);

		return definitions;
	}

}
