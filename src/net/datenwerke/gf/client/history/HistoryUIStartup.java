package net.datenwerke.gf.client.history;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class HistoryUIStartup {

   @Inject
   public HistoryUIStartup(HookHandlerService hookHandler, HistoryUiService historyService,
         final WaitOnEventUIService waitOnEventService) {

      waitOnEventService.callbackOnEvent(DispatcherService.REPORTSERVER_EVENT_USER_LOGGED_IN_APPLICATION_LOADED,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  waitOnEventService.signalProcessingDone(ticket);
                  Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                     @Override
                     public void execute() {
                        History.fireCurrentHistoryState();
                     }
                  });
               }
            });

   }

}
