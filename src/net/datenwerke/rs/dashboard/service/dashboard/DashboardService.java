package net.datenwerke.rs.dashboard.service.dashboard;

import java.util.Collection;

import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.UserDashboard;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface DashboardService extends TreeDBManager<AbstractDashboardManagerNode> {
	
	public static final String USER_PROPERTY_PRIMARY_DASHBOARD = "dashboard:primaryDashboard";

	void removeDashboardFor(User user);
	
	Dashboard getExplicitPrimaryDashboard(User user);

	UserDashboard getUserDashboard(User user);

	DashboardContainer getDashboardFor(User user);

	DashboardContainer merge(DashboardContainer dashboardContainer);

	Dashboard merge(Dashboard dashboard);

	void remove(Dashboard dashboard);

	void persist(Dadget dadget);

	void persist(Dashboard dashboard);

	void remove(Dadget dadget);

	Dadget merge(Dadget dadget);

	DashboardContainer getDashboardContainerFor(Dashboard db);

	UserDashboard getUserDashboardFor(DashboardContainer container);

	Dashboard getDashboardById(Long id);

	Dashboard getDashboardFor(Dadget dadget);

	DashboardNode getNodeFor(Dashboard dashboard);
	
	boolean isOwner(User user, Dashboard dashboard);

	Collection<Dashboard> getAllDashboards();

	Collection<Dashboard> getDashboards();

	void setPrimaryDashboard(Dashboard dashboard);

}
