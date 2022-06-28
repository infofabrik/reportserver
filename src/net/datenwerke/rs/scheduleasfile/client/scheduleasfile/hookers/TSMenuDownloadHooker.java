package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class TSMenuDownloadHooker implements TsFavoriteMenuHook {

   private final TeamSpaceUIService teamSpaceService;

   @Inject
   public TSMenuDownloadHooker(
         TeamSpaceUIService teamSpaceService
         ) {

      /* store objects */
      this.teamSpaceService = teamSpaceService;
   }

   @Override
   public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector,
         final TsDiskMainComponent mainComponent) {
      if (null == items || items.isEmpty() || items.size() > 1)
         return false;
      if (!teamSpaceService.isGuest(mainComponent.getCurrentSpace()))
         return false;

      final AbstractTsDiskNodeDto item = items.get(0);
      if (!(item instanceof ExecutedReportFileReferenceDto) && !(item instanceof TsDiskFileReferenceDto))
         return false;

      MenuItem downloadItem = new DwMenuItem(BaseMessages.INSTANCE.save(), BaseIcon.DOWNLOAD);
      downloadItem.addSelectionHandler(event -> {
         String id = String.valueOf(item.getId());
         String servlet = null;
         if(item instanceof ExecutedReportFileReferenceDto)
            servlet = ScheduleAsFileUiModule.EXPORT_SERVLET;
         else if (item instanceof TsDiskFileReferenceDto)
            servlet = ScheduleAsFileUiModule.DISK_FILE_EXPORT_SERVLET;
         
         String url = GWT.getModuleBaseURL() + servlet + "?fileId=" + id //$NON-NLS-1$
               + "&download=true";

         ClientDownloadHelper.triggerDownload(url);
      });

      menu.add(downloadItem);

      return true;
   }

}
