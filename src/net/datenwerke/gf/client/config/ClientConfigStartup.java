package net.datenwerke.gf.client.config;

import javax.inject.Inject;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class ClientConfigStartup {

	@Inject
	public ClientConfigStartup(	
			HookHandlerService hookHandlerService, 
			final WaitOnEventUIService waitOnEventService,
			final ClientConfigService clientConfigService,
			final ClientConfigDao dao
		) {
		
		/* load generic rights after login */
		waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);

				dao.getClientConfig(ClientConfigModule.MAIN_CLIENT_CONFIG, new RsAsyncCallback<String>(){
					@Override
					public void onSuccess(String result) {
						clientConfigService.setMainConfig(result);
						
						waitOnEventService.triggerEvent(ClientConfigModule.CLIENT_CONFIG_FILE_LOADED); 
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
