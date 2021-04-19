package net.datenwerke.rs.legacysaiku.service.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

public class MondrianDatasourceProviderHooker implements DatasourceProviderHook {

	@Override
	public Collection<? extends Class<? extends DatasourceDefinition>> getDatasources() {
		return (Collection<? extends Class<? extends DatasourceDefinition>>) Arrays.asList(new Class[]{MondrianDatasource.class}); 
	}

}
