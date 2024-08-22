package net.datenwerke.rs.base.service.datasources.transformers;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface DataSourceDefinitionTransformer<T> extends Hook {

   T transform(DatasourceContainerProvider datasourceContainerProvider, Class<?> dst, ParameterSet parameters)
         throws UnsupportedDriverException, DatabaseConnectionException;

   boolean consumes(DatasourceContainerProvider datasourceContainerProvider, Class<?> dst);

}
