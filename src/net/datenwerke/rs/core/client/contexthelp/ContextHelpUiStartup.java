package net.datenwerke.rs.core.client.contexthelp;

import javax.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigModule;
import net.datenwerke.gf.client.config.ClientConfigService;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;

public class ContextHelpUiStartup {

	@Inject
	public ContextHelpUiStartup(
			final WaitOnEventUIService waitOnEventService,
			final ClientConfigService clientConfigService,
			final ContextHelpUiService contextHelpService
			){
		waitOnEventService.callbackOnEvent(ClientConfigModule.CLIENT_CONFIG_FILE_LOADED, new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);

				try{
					boolean enable = clientConfigService.getBoolean("contexthelp.enable", false);
					if(enable){
						contextHelpService.enable();
					}
				} catch(Exception e){}
			}
		});
	}
}
