package net.datenwerke.gf.client.theme;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;

public class ThemeUiStartup {

	@Inject
	public ThemeUiStartup(	HookHandlerService hookHandlerService, 
			final WaitOnEventUIService waitOnEventService,
			final ThemeDao themeDao,
			final ThemeUiService themeService
		) {
		
		SynchronousCallbackOnEventTrigger callback = new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				themeDao.loadUiTheme(new RsAsyncCallback<ThemeUiConfig>(){
					public void onSuccess(ThemeUiConfig result) {
						themeService.setThemeConfig(result);
						waitOnEventService.signalProcessingDone(ticket);
					};
					
					@Override
					public void onFailure(Throwable caught) {
						super.onFailure(caught);
						waitOnEventService.signalProcessingDone(ticket);
					}
				});
			}
		};
		
		waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, callback);
		waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_BEFORE_AUTH_WINDOW_LOAD, callback);
	}
}
