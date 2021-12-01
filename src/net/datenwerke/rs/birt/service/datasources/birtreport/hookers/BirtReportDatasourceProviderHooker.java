package net.datenwerke.rs.birt.service.datasources.birtreport.hookers;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;

public class BirtReportDatasourceProviderHooker implements
		DatasourceProviderHook {

	@Override
	public Collection<? extends Class<? extends DatasourceDefinition>> getDatasources() {
		return (Collection<? extends Class<? extends DatasourceDefinition>>) Arrays.asList(new Class[]{BirtReportDatasourceDefinition.class});
	}

}
