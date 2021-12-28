package net.datenwerke.rs.base.service.datasources.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;

public class BaseDatasourceProviderHooker implements DatasourceProviderHook {

   @Override
   public Collection<? extends Class<? extends DatasourceDefinition>> getDatasources() {
      return (Collection<? extends Class<? extends DatasourceDefinition>>) Arrays
            .asList(new Class[] { DatabaseDatasource.class, CsvDatasource.class });
   }

}
