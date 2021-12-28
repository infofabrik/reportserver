package net.datenwerke.rs.dashboard.server.dashboard;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardTreeLoader;
import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardTreeManager;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardManagerService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;

/**
 * 
 *
 */
@Singleton
public class DashboardTreeRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractDashboardManagerNode> 
		implements 
			DashboardTreeLoader, 
			DashboardTreeManager
			 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246061438291322619L;

	@Inject
	public DashboardTreeRpcServiceImpl(
		DtoService dtoService,
		DashboardManagerService manager,
		SecurityService securityService,
		EntityClonerService entityClonerService
		) {
		super(manager, dtoService, securityService, entityClonerService);
	}

	@Override
	protected void doSetInitialProperties(AbstractDashboardManagerNode inserted) {
		if(inserted instanceof DashboardNode){
			DashboardNode dashboardNode = (DashboardNode) inserted;
			dashboardNode.setDashboard(new Dashboard());
		}
	}

	@Override
	protected boolean allowDuplicateNode(AbstractDashboardManagerNode realNode) {
		return true;
	}
	
	@Override
	protected void nodeCloned(AbstractDashboardManagerNode clonedNode) {
		if(! (clonedNode instanceof AbstractDashboardManagerNode))
			throw new IllegalArgumentException();
		
		if (clonedNode instanceof DadgetNode) {
			DadgetNode dadgetNode = (DadgetNode) clonedNode;
			dadgetNode.setName(dadgetNode.getName() == null ? "copy" : dadgetNode.getName() + " (copy)");
		} else if (clonedNode instanceof DashboardNode) {
			DashboardNode dashboardNode = (DashboardNode) clonedNode;
			dashboardNode.setName(dashboardNode.getName() == null ? "copy" : dashboardNode.getName() + " (copy)");
		}
		
	}
}
	