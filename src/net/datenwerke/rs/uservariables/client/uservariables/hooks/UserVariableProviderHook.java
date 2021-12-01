package net.datenwerke.rs.uservariables.client.uservariables.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfigurator;

public interface UserVariableProviderHook extends Hook {

	public Collection<UserVariableConfigurator> userVariableProviderHook_getConfigurators();
}
