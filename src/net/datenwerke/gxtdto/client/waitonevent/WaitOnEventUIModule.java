package net.datenwerke.gxtdto.client.waitonevent;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class WaitOnEventUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind services */
		bind(WaitOnEventUIService.class).to(WaitOnEventUIServiceImpl.class).in(Singleton.class);
		
		/* static injection */
		requestStaticInjection(AsynchronousCallbackOnEventTrigger.class);
	}

}
