package net.datenwerke.rs.dashboard.service.dashboard;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard__;
import net.datenwerke.rs.dashboard.service.dashboard.entities.UserDashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.UserDashboard__;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

public class DashboardServiceImpl extends SecuredTreeDBManagerImpl<AbstractDashboardManagerNode> implements DashboardService{

	private final Provider<EntityManager> entityManagerProvider;
	private final UserPropertiesService userPropertiesService;
	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	private final UserManagerService userService;

	@Inject
	public DashboardServiceImpl(
		Provider<EntityManager> entityManagerProvider,
		UserPropertiesService userPropertiesService,
		Provider<AuthenticatorService> authenticatorServiceProvider,
		UserManagerService userService
		){
		this.entityManagerProvider = entityManagerProvider;
		this.userPropertiesService = userPropertiesService;
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.userService = userService;
	}
	
	@Override
	public Dashboard getExplicitPrimaryDashboard(User user) {
		
		UserProperty property = userPropertiesService.getProperty(user, USER_PROPERTY_PRIMARY_DASHBOARD);
		if(null != property){
			try{
				long id = Long.valueOf(property.getValue());
				Dashboard dashboard = getDashboardById(id);
				UserDashboard ud = getUserDashboard(user);
				if (null == ud) 
					return null;
				if(null != dashboard && isOwner(user, dashboard))
					return dashboard;
			} catch(NumberFormatException e){
			} catch(NoResultException e){
			}
		}
		
		return null;
	}
	
	@Override
	public boolean isOwner(User user, Dashboard dashboard) {
		UserDashboard ud = getUserDashboard(user);
		if (null == ud) 
			return false;
		return ud.getDashboardContainer().getDashboards().contains(dashboard);
	}
	
	@Override
	public DashboardContainer getDashboardFor(User user) {
		if(null == user)
			return null;
		
		UserDashboard ud = getUserDashboard(user);
		if(null == ud)
			ud = createDashboardForUser(user);
		
		DashboardContainer container = ud.getDashboardContainer();
		Dashboard explicitDashboard = getExplicitPrimaryDashboard(user);
		if (null != explicitDashboard) {
			for (Dashboard userDashboard: container.getDashboards()) {
				if (userDashboard == explicitDashboard) {
					userDashboard.setPrimary(true);
				}
			}
		}
		return container;
	}
	
	@Override
	@QueryByAttribute(where=UserDashboard__.dashboardContainer)
	public UserDashboard getUserDashboardFor(DashboardContainer container) {
		return null; //magic
	}
	
	public UserDashboard createDashboardForUser(User user) {
		if(null == user)
			throw new IllegalArgumentException();
		
		UserDashboard ud = getUserDashboard(user);
		if(null == ud){
			ud = new UserDashboard();
			ud.setDashboardContainer(new DashboardContainer());
			ud.setUser(user);
			persist(ud);
		}
		return ud;
	}
	
	@Override
	@SimpleQuery(from=DashboardContainer.class,join=@Join(joinAttribute=DashboardContainer__.dashboards, where=@Predicate(attribute="",value="db")))
	public DashboardContainer getDashboardContainerFor(@Named("db") Dashboard db) {
		return null; // magic
	}
	
	@Override
	@SimpleQuery(from=Dashboard.class,join=@Join(joinAttribute=Dashboard__.dadgets, where=@Predicate(attribute="",value="dadget")))
	public Dashboard getDashboardFor(@Named("dadget")Dadget dadget) {
		return null; // magic
	}
	
	@Override
	@QueryByAttribute(where=DashboardNode__.dashboard)
	public DashboardNode getNodeFor(Dashboard dashboard) {
		return null;
	}
	
	@Override
	@QueryById(from=Dashboard.class)
	public Dashboard getDashboardById(Long id) {
		return null; //magic
	}
	
	@Override
	public void removeDashboardFor(User user) {
		UserDashboard ud = getUserDashboard(user);
		if(null != ud)
			remove(ud);
	}

	@FireRemoveEntityEvents
	public void remove(UserDashboard ud) {
		if(null == ud)
			return;
		EntityManager em = entityManagerProvider.get();
		ud = em.find(ud.getClass(), ud.getId());
		if(null != ud)
			em.remove(ud);		
	}
	
	@Override
	@FireRemoveEntityEvents
	public void remove(Dashboard dashboard) {
		EntityManager em = entityManagerProvider.get();
		dashboard = em.find(dashboard.getClass(), dashboard.getId());
		if(null != dashboard)
			em.remove(dashboard);		
	}
	
	@Override
	@FireRemoveEntityEvents
	public void remove(Dadget dadget) {
		EntityManager em = entityManagerProvider.get();
		dadget = em.find(dadget.getClass(), dadget.getId());
		if(null != dadget)
			em.remove(dadget);		
	}
	
	@FirePersistEntityEvents
	public void persist(UserDashboard ud) {
		entityManagerProvider.get().persist(ud);
	}

	@Override
	@QueryByAttribute(where=UserDashboard__.user)
	public UserDashboard getUserDashboard(User user) {
		return null; //magic
	}
	
	@Override
	@FireMergeEntityEvents
	public DashboardContainer merge(DashboardContainer dashboardContainer) {
		return entityManagerProvider.get().merge(dashboardContainer);
	}
	
	@Override
	@FireMergeEntityEvents
	public Dashboard merge(Dashboard dashboard) {
		return entityManagerProvider.get().merge(dashboard);
	}
	
	@Override
	@FireMergeEntityEvents
	public Dadget merge(Dadget dadget) {
		return entityManagerProvider.get().merge(dadget);
	}

	@Override
	@FirePersistEntityEvents
	public void persist(Dadget dadget) {
		entityManagerProvider.get().persist(dadget);
	}
	
	@Override
	@FirePersistEntityEvents
	public void persist(Dashboard dashboard) {
		entityManagerProvider.get().persist(dashboard);
	}

	@Override
	@SimpleQuery
	public Collection<Dashboard> getAllDashboards() {
		return null; // by magic
	}

	@Override
	public Collection<Dashboard> getDashboards() {
		User user = authenticatorServiceProvider.get().getCurrentUser();
		DashboardContainer container = getDashboardFor(user);
		return container.getDashboards();
	}

	@Override
	public void setPrimaryDashboard(Dashboard dashboard) {
		User currentUser = authenticatorServiceProvider.get().getCurrentUser();

		UserProperty property = userPropertiesService.getProperty(currentUser, USER_PROPERTY_PRIMARY_DASHBOARD);
		if(null == property){
			if(null != dashboard){
				property = new UserProperty(USER_PROPERTY_PRIMARY_DASHBOARD, String.valueOf(dashboard.getId()));
				userPropertiesService.setProperty(currentUser, property);
			}
		} else{
			if(null != dashboard)
				property.setValue(dashboard.getId());
			else
				userPropertiesService.removeProperty(currentUser, property);
		}
		
		userService.merge(currentUser);
	}

	@Override
	@QueryById
	public AbstractDashboardManagerNode getNodeById(long id) {
		return null; // magic
	}

	@Override
	@QueryByAttribute(where=AbstractDashboardManagerNode__.parent,type=PredicateType.IS_NULL)
	public List<AbstractDashboardManagerNode> getRoots() {
		return null; // magic
	}

	@Override
	@SimpleQuery
	public List<AbstractDashboardManagerNode> getAllNodes(){
		return null;
	}

}