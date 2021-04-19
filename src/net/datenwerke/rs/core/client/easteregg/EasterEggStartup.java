package net.datenwerke.rs.core.client.easteregg;

import javax.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigModule;
import net.datenwerke.gf.client.config.ClientConfigService;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewSpecialViewHandler;

public class EasterEggStartup {

	@Inject
	public EasterEggStartup(
			final HookHandlerService hookHandler,
			
			final RsPacmanView pacman,
			final RsPacmanObjectInfo pacmanObjectView,
			final ClientConfigService clientConfigService,
			
			final WaitOnEventUIService waitOnEventService
			){
			
		waitOnEventService.callbackOnEvent(ClientConfigModule.CLIENT_CONFIG_FILE_LOADED, new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);

				try{
					boolean disable = clientConfigService.getBoolean("eastereggs.disable", false);
					if(! disable){
						/* easter egg */
						hookHandler.attachHooker(UrlViewSpecialViewHandler.class, pacman);
				 		hookHandler.attachHooker(
							ObjectPreviewTabProviderHook.class,
							pacmanObjectView,
							HookHandlerService.PRIORITY_LOW
				 			);
					}
				} catch(Exception e){}
			}
		});
		
		

	}
}
