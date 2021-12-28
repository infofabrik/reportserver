package net.datenwerke.gf.client;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gf.client.homepage.HomepageDao;
import net.datenwerke.gf.client.uiutils.date.simpleform.DateFormulaProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.servercommunication.callback.HandledAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class DwGwtFrameworkUIStartup {

   @Inject
   public DwGwtFrameworkUIStartup(Provider<DateFormulaProvider> dateFormulaProvider,

         HookHandlerService hookHandler, final HomepageDao homepageDao, final WaitOnEventUIService waitOnEventService) {

      /* simple form */
      hookHandler.attachHooker(FormFieldProviderHook.class, dateFormulaProvider, HookHandlerService.PRIORITY_LOW);

      /* Set Window Title */
      waitOnEventService.callbackOnEvent(DispatcherService.REPORTSERVER_EVENT_BEFORE_STARTUP,
            new SynchronousCallbackOnEventTrigger() {

               public void execute(final WaitOnEventTicket ticket) {
                  homepageDao.getPageTitle(new HandledAsyncCallback<String>(null) {
                     @Override
                     public void doOnSuccess(String result) {
                        if (null != result) {
                           Window.setTitle(result);
                        }
                     }
                  });
                  waitOnEventService.signalProcessingDone(ticket);
               }
            });
   }

}
