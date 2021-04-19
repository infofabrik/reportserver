package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.User;

public class HandleUserRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DashboardService dashboardService;
	private final DadgetService dadgetService;

	@Inject
	public HandleUserRemoveEventHandler(DashboardService dashboardService,
			DadgetService dadgetService) {
		this.dashboardService = dashboardService;
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		/* remove dashboard */
		User user = (User) event.getObject();
		dashboardService.removeDashboardFor(user);
		
		/* remove bookmark list */
		FavoriteList favoriteList = dadgetService.loadFavoriteList(user);
		if(null != favoriteList){
			dadgetService.remove(favoriteList);
		}
	}

}
