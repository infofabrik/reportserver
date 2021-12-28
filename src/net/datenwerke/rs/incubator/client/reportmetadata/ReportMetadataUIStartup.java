package net.datenwerke.rs.incubator.client.reportmetadata;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.client.reportmetadata.hookers.MainPanelViewProviderHooker;

public class ReportMetadataUIStartup {

   @Inject
   public ReportMetadataUIStartup(HookHandlerService hookHandler,

         MainPanelViewProviderHooker mainPanelViewProvider) {

      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider, HookHandlerService.PRIORITY_LOW);
   }
}
