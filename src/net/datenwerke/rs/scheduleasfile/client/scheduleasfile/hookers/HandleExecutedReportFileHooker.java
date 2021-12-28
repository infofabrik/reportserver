package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class HandleExecutedReportFileHooker implements
		GeneralReferenceHandlerHook {

	private final UtilsUIService utilsService;
	
	@Inject
	public HandleExecutedReportFileHooker(
		UtilsUIService utilsService
		) {
		
		/* store objects */
		this.utilsService = utilsService;
	}

	@Override
	public boolean consumes(TsDiskGeneralReferenceDto item) {	
		return item instanceof ExecutedReportFileReferenceDto;
	}

	@Override
	public void handle(TsDiskGeneralReferenceDto item, TsDiskMainComponent mainComponent) {
		String id = String.valueOf(item.getId());
		String url = GWT.getModuleBaseURL() +  ScheduleAsFileUiModule.EXPORT_SERVLET + "?fileId=" + id + "&download=false"; //$NON-NLS-1$
		
		utilsService.redirectInPopup(url);
	}

}
