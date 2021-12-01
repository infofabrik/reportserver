package net.datenwerke.rs.core.service.datasinkmanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;

public interface DatasinkProviderHook extends Hook {

	Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks();

}
