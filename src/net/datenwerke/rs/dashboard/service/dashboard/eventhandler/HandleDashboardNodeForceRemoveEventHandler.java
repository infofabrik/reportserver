package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleDashboardNodeForceRemoveEventHandler implements
		EventHandler<ForceRemoveEntityEvent> {

	private final DadgetService dadgetService;
	private final DashboardService dashboardService;

	@Inject
	public HandleDashboardNodeForceRemoveEventHandler(DadgetService dadgetService, DashboardService dashboardService) {
		this.dadgetService = dadgetService;
		this.dashboardService = dashboardService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		DashboardNode node = (DashboardNode) event.getObject();
		
		List<DashboardReference> references = dadgetService.getDashboardContainerssWith(node);
		
		if(null != references && ! references.isEmpty()){
			for(DashboardReference reference : references){
				reference.setDashboardNode(null);
				dashboardService.merge(reference);
			}
		}
	}

}
