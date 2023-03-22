package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Optional;
import java.util.Set;

import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DatasinkTreeService extends TreeDBManager<AbstractDatasinkManagerNode> {

   Set<Class<? extends DatasinkDefinition>> getInstalledDatasinkDefinitions();

   DatasinkDefinition getDatasinkByName(String name);

   DatasinkDefinition getDatasinkById(Long id);

   DatasinkFolder getDatasinkFolderByName(String name);

   DatasinkContainer merge(DatasinkContainer container);

   <T extends DatasinkDefinition> Optional<T> getDefaultDatasink(Class<T> type, String defaultDatasinkIdProperty,
         String defaultDatasinkNameProperty);

   long getDatasinkIdFromKey(String key);
   
   DatasinkDefinition getDatasinkByKey(String key);
}
