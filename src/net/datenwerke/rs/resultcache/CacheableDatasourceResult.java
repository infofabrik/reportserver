package net.datenwerke.rs.resultcache;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface CacheableDatasourceResult extends CacheableResult {

   public boolean consumes(DatasourceDefinitionConfig config, ParameterSet parameters);

}
