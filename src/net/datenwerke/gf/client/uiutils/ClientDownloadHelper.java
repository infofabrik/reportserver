package net.datenwerke.gf.client.uiutils;

import javax.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigService;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;

public class ClientDownloadHelper {

   @Inject
   private static UtilsUIService utilsUIService;

   @Inject
   private static ClientConfigService clientConfigService;

   public static void triggerDownload(String url) {
      boolean usePopups = clientConfigService.getBoolean("popupDownloadMethod", false);
      if (usePopups) {
         utilsUIService.redirectInPopup(url);
      } else {
         utilsUIService.triggerDownload(url);
      }
   }

}
