package net.datenwerke.rs.core.client.parameters.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;

/**
 * 
 *
 */
public interface ParameterProviderHook extends Hook {
	
	public Collection<ParameterConfigurator> parameterProviderHook_getConfigurators();
}
