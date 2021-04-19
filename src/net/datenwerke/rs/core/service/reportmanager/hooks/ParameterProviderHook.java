package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

public interface ParameterProviderHook extends Hook {

	Collection<? extends Class<? extends ParameterDefinition>> getParameterDefinitions();

}
