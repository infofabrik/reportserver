package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex;

import com.google.inject.Inject;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.security.ExportGenericTargetIdentifier;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.hookers.ExportButtonHook;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;

public class FileServerExportUIStartup {

   @Inject
   public FileServerExportUIStartup(final HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final SecurityUIService securityService, final ExportButtonHook exportButtonHook) {

      /* test if user has rights to see report manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(ExportGenericTargetIdentifier.class, ExecuteDto.class))
                     hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, exportButtonHook);

                  waitOnEventService.signalProcessingDone(ticket);
               }
            });

   }
}
