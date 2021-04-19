package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Set;

import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DatasinkService extends TreeDBManager<AbstractDatasinkManagerNode> {

	public Set<Class<? extends DatasinkDefinition>> getInstalledDatasinkDefinitions();
	
	public DatasinkDefinition getDatasinkByName(String name);
	
	public DatasinkDefinition getDatasinkById(Long id);
	
	public DatasinkFolder getDatasinkFolderByName(String name);
	
	public DatasinkContainer merge(DatasinkContainer container);

}
