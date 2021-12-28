package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleReportReferenceForceRemoveEventHandler implements
		EventHandler<ForceRemoveEntityEvent> {

	private final DadgetService dadgetService;

	@Inject
	public HandleReportReferenceForceRemoveEventHandler(DadgetService dadgetService) {
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		TsDiskReportReference node = (TsDiskReportReference) event.getObject();
		
		dadgetService.removeFromReportDadgets(node);
	}

}
