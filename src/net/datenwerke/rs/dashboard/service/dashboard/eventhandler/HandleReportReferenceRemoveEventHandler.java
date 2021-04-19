package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import java.util.Collection;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class HandleReportReferenceRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DadgetService dadgetService;

	@Inject
	public HandleReportReferenceRemoveEventHandler(DadgetService dadgetService) {
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		TsDiskReportReference node = (TsDiskReportReference) event.getObject();
		
		Collection<ReportDadget> container = dadgetService.getReportDadgetsWith(node);
		if(null != container && ! container.isEmpty()){
			String error = "Report is referenced in dadgets"; 
			throw new NeedForcefulDeleteException(error);
		}
	}

}
