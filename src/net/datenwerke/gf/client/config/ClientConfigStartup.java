package net.datenwerke.gf.client.config;

import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.PageSizeConfig;

public class ClientConfigStartup {

   @Inject
   public ClientConfigStartup(
         HookHandlerService hookHandlerService, 
         final WaitOnEventUIService waitOnEventService,
         final ClientConfigService clientConfigService, 
         final ClientConfigDao clientConfigDao,
         final TableReportUtilityDao tableReportUtilityDao
         ) {

      /* load generic rights after login */
      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN,
            new SynchronousCallbackOnEventTrigger() {
               @Override
               public void execute(final WaitOnEventTicket ticket) {
                  waitOnEventService.signalProcessingDone(ticket);

                  clientConfigDao.getClientConfig(ClientConfigModule.MAIN_CLIENT_CONFIG, new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        clientConfigService.setMainConfig(result);
                        
                        tableReportUtilityDao.getPreviewPageSizeConfigs(new RsAsyncCallback<List<PageSizeConfig>>() {
                           @Override
                           public void onSuccess(List<PageSizeConfig> result) {
                              clientConfigService.setPreviewPageSizeConfigs(result);
                              
                              waitOnEventService.triggerEvent(ClientConfigModule.CLIENT_CONFIG_FILE_LOADED);
                           }
                           
                           public void onFailure(Throwable caught) {
                              
                           }
                        });
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                        super.onFailure(caught);
                     }
                  });
               }
            });
   }
}
