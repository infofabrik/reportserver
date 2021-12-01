package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class HandleDiskNodeRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DadgetService dadgetService;

	@Inject
	public HandleDiskNodeRemoveEventHandler(DadgetService dadgetService) {
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		AbstractTsDiskNode node = (AbstractTsDiskNode) event.getObject();
		
		dadgetService.removeFromFavorites(node);
	}

}
