package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import java.util.List;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

import com.google.inject.Inject;

public class HandleDadgetNodeForceRemoveEventHandler implements
		EventHandler<ForceRemoveEntityEvent> {

	private final DadgetService dadgetService;
	private final DashboardService dashboardService;

	@Inject
	public HandleDadgetNodeForceRemoveEventHandler(DadgetService dadgetService, DashboardService dashboardService) {
		this.dadgetService = dadgetService;
		this.dashboardService = dashboardService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		DadgetNode node = (DadgetNode) event.getObject();
		
		List<LibraryDadget> libraryDadgets = dadgetService.getLibraryDadgetsWith(node);
		
		if(null != libraryDadgets && ! libraryDadgets.isEmpty()){
			for(LibraryDadget dadget : libraryDadgets){
				dadget.setDadgetNode(null);
				dashboardService.merge(dadget);
			}
		}
	}

}
