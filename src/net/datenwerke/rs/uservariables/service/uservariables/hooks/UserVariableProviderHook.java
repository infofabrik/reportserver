package net.datenwerke.rs.uservariables.service.uservariables.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;

public interface UserVariableProviderHook extends Hook {

   Collection<? extends UserVariableDefinition> getVariables();

}
