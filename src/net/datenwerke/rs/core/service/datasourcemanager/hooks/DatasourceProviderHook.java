package net.datenwerke.rs.core.service.datasourcemanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;

public interface DatasourceProviderHook extends Hook {

	Collection<? extends Class<? extends DatasourceDefinition>> getDatasources();

}
