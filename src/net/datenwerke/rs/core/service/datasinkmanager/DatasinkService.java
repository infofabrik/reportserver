package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Set;

import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DatasinkService extends TreeDBManager<AbstractDatasinkManagerNode> {

   Set<Class<? extends DatasinkDefinition>> getInstalledDatasinkDefinitions();

   DatasinkDefinition getDatasinkByName(String name);

   DatasinkDefinition getDatasinkById(Long id);

   DatasinkFolder getDatasinkFolderByName(String name);

   DatasinkContainer merge(DatasinkContainer container);
   
}
