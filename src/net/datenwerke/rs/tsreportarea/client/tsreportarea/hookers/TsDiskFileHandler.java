package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;


public class TsDiskFileHandler implements GeneralReferenceHandlerHook {

   private final UtilsUIService utilsService;
   
   @Inject
   public TsDiskFileHandler(
         UtilsUIService utilsService) {
      this.utilsService = utilsService;
   }
   
   @Override
   public boolean consumes(TsDiskGeneralReferenceDto item) {
      return item instanceof TsDiskFileReferenceDto;
   }

   @Override
   public void handle(TsDiskGeneralReferenceDto item, TsDiskMainComponent mainComponent) {
      String id = String.valueOf(item.getId());
      String url = GWT.getModuleBaseURL() + ScheduleAsFileUiModule.DISK_FILE_EXPORT_SERVLET + "?fileId=" + id
            + "&download=false";

      utilsService.redirectInPopup(url);
      
   }
   
}
