package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode__;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition__;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder__;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;

/**
 * 
 *
 */
public class DatasinkServiceImpl extends SecuredTreeDBManagerImpl<AbstractDatasinkManagerNode> implements DatasinkService {

	private final Provider<Set<Class<? extends DatasinkDefinition>>> installedDatasinkDefinitions;
	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public DatasinkServiceImpl(
		Provider<EntityManager> entityManagerProvider,
		@ReportServerDatasinkDefinitions Provider<Set<Class<? extends DatasinkDefinition>>> installedDataSourceDefinitions
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.installedDatasinkDefinitions = installedDataSourceDefinitions;
	}

	@Override
	public Set<Class<? extends DatasinkDefinition>> getInstalledDatasinkDefinitions() {
		return installedDatasinkDefinitions.get();
	}
	
	@QueryByAttribute(where=DatasinkDefinition__.name)
	@Override
	public DatasinkDefinition getDatasinkByName(String name) {
		return null; // by magic
	}
	
	@QueryByAttribute(where=DatasinkDefinition__.id)
	@Override
	public DatasinkDefinition getDatasinkById(Long id) {
		return null; // by magic
	}

	@QueryByAttribute(where=DatasinkFolder__.name)
	@Override
	public DatasinkFolder getDatasinkFolderByName(@Named("name") String name) {
		return null; // by magic
	} 
	
	@Override
	@QueryByAttribute(where=AbstractDatasinkManagerNode__.parent,type=PredicateType.IS_NULL)
	public List<AbstractDatasinkManagerNode> getRoots() {
		return null; // magic
	}
	
	@Override
	@SimpleQuery
	public List<AbstractDatasinkManagerNode> getAllNodes(){
		return null;
	}
	
	@Override
	@QueryById
	public AbstractDatasinkManagerNode getNodeById(long id) {
		return null; // magic
	}

	@Override
	@FireMergeEntityEvents
	public DatasinkContainer merge(DatasinkContainer container) {
		return entityManagerProvider.get().merge(container);
	}

}
