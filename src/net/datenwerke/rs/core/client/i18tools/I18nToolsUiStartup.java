package net.datenwerke.rs.core.client.i18tools;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

import com.google.inject.Inject;

public class I18nToolsUiStartup {

	@Inject
	public I18nToolsUiStartup(
			final I18nToolsDao dao,
			final I18nToolsUiServiceImpl service,
			final WaitOnEventUIService waitOnEventService, 
			final FormatUiHelper formatUiHelper
			){

		/* load generic rights after login */
		waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				dao.getDecimalSeparator(new RsAsyncCallback<String>(){
					@Override
					public void onSuccess(String result) {
						service.setUserDecimalSeparator(result);
					}
				});

				dao.getFormatPatterns(new RsAsyncCallback<FormatPatternsDto>(){
					public void onSuccess(FormatPatternsDto result) {
						formatUiHelper.setFormatPatterns(result);
					}
				});

				waitOnEventService.signalProcessingDone(ticket);
			}
		});
	}
}
