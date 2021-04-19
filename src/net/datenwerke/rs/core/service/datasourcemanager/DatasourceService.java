package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DatasourceService extends TreeDBManager<AbstractDatasourceManagerNode> {

	public Set<Class<? extends DatasourceDefinition>> getInstalledDataSourceDefinitions();
	
	public DatasourceDefinition getDatasourceByName(String name);
	
	public DatasourceDefinition getDatasourceById(Long id);
	
	public DatasourceFolder getDatasourceFolderByName(String name);
	
	public String getDefaultDatasourceId();

	public DatasourceDefinition getDefaultDatasource();

	public DatasourceContainer merge(DatasourceContainer container);

	public void remove(DatasourceDefinitionConfig datasourceConfig);

}
