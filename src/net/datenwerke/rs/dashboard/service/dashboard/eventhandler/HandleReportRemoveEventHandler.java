package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class HandleReportRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DadgetService dadgetService;

	@Inject
	public HandleReportRemoveEventHandler(DadgetService dadgetService) {
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		Report node = (Report) event.getObject();
		
		dadgetService.removeFromReportDadgets(node);
	}

}
