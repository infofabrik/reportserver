package net.datenwerke.gxtdto.client.i18n.remotemessages;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.i18n.remotemessages.rpc.RemoteMessageRpcServiceAsync;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;

public class RemoteMessageUiStartup {

   public static final String REPORTSERVER_EVENT_BEFORE_STARTUP = "REPORTSERVER_EVENT_BEFORE_STARTUP";

   @Inject
   public RemoteMessageUiStartup(final RemoteMessageRpcServiceAsync remoteMessageService,
         final WaitOnEventUIService waitOnEventService) {
      waitOnEventService.callbackOnEvent(REPORTSERVER_EVENT_BEFORE_STARTUP, new SynchronousCallbackOnEventTrigger() {

         public void execute(final WaitOnEventTicket ticket) {
            RemoteMessageCache.getInstance().init(remoteMessageService, new AsyncCallback<Void>() {

               @Override
               public void onFailure(Throwable caught) {
                  waitOnEventService.signalProcessingDone(ticket);
               }

               @Override
               public void onSuccess(Void result) {
                  waitOnEventService.signalProcessingDone(ticket);
               }
            });

         }
      });
   }

}
