package net.datenwerke.rs.core.service.datasourcemanager.hookers.factory;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.DatasourceDefaultMergeHooker;

public interface DatasourceDefaultMergeHookerFactory {
   
   DatasourceDefaultMergeHooker<? extends DatasourceDefinition> create(Class<? extends DatasourceDefinition> targetClass);

}
