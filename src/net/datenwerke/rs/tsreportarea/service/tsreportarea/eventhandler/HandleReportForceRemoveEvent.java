package net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleReportForceRemoveEvent implements EventHandler<ForceRemoveEntityEvent> {


	private final TsDiskService diskService;
	
	@Inject
	public HandleReportForceRemoveEvent(TsDiskService diskService) {
		this.diskService = diskService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		Report report = (Report) event.getObject();
		
		List<TsDiskReportReference> references = diskService.getReferencesTo(report);
		if(null != references && ! references.isEmpty()){
			for(TsDiskReportReference ref : references){
				diskService.remove(ref);
			}
		}
	}


}
