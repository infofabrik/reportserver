package net.datenwerke.rs.adminutils.client.suuser;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.suuser.security.SuGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.suuser.security.SuViewSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class SuUserUIStartup {

   @Inject
   public SuUserUIStartup(final HookHandlerService hookHandler, final SecurityUIService securityService,
         final WaitOnEventUIService waitOnEventService,
         final SuUserUiService suUserUiService,
         SuViewSecurityTargetDomainHooker securityTargetDomain) {

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(SuGenericTargetIdentifier.class, ExecuteDto.class))
                     attachSu(suUserUiService);

                  waitOnEventService.signalProcessingDone(ticket);
               }
            });
   }

   protected void attachSu(final SuUserUiService suUserUiService) {
      Event.addNativePreviewHandler(new NativePreviewHandler() {
         @Override
         public void onPreviewNativeEvent(NativePreviewEvent event) {
            if (event.getTypeInt() == Event.ONKEYUP) {
               boolean ctrlKey = event.getNativeEvent().getCtrlKey();
               if (ctrlKey) {
                  int keyCode = event.getNativeEvent().getKeyCode();
                  if (ctrlKey && (keyCode == 'L' || keyCode == 'l')) {
                     suUserUiService.openSuWindow();
                  }
               }
            }
         }

      });
   }
}
