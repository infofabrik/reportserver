package net.datenwerke.rs.dashboard.service.dashboard;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode__;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;

public class DashboardManagerServiceImpl extends SecuredTreeDBManagerImpl<AbstractDashboardManagerNode> implements DashboardManagerService {

	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public DashboardManagerServiceImpl(
			Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@QueryById
	@Override
	public AbstractDashboardManagerNode getNodeById(long id) {
		return null; // magic
	}

	@Override
	@QueryByAttribute(where=AbstractDashboardManagerNode__.parent,type=PredicateType.IS_NULL)
	public List<AbstractDashboardManagerNode> getRoots() {
		return null;
	}

	@Override
	@SimpleQuery
	public List<AbstractDashboardManagerNode> getAllNodes() {
		return null;
	}

	

}
