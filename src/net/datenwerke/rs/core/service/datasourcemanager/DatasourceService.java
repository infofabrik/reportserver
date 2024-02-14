package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DatasourceService extends TreeDBManager<AbstractDatasourceManagerNode> {
   
   Set<Class<? extends DatasourceDefinition>> getInstalledDataSourceDefinitions();

   DatasourceDefinition getDatasourceByName(String name);

   DatasourceDefinition getDatasourceById(Long id);

   DatasourceFolder getDatasourceFolderByName(String name);

   String getDefaultDatasourceId();

   DatasourceDefinition getDefaultDatasource();

   DatasourceContainer merge(DatasourceContainer container);

   void remove(DatasourceDefinitionConfig datasourceConfig);

   long getDatasourceIdFromKey(String key);
   
   DatasourceDefinition getDatasourceByKey(String key);
   
}
