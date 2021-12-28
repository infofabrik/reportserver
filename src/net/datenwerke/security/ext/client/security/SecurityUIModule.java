package net.datenwerke.security.ext.client.security;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.ext.client.security.ui.SecurityView;

public class SecurityUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(SecurityUIService.class).to(SecurityUIServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(SecurityUIStartup.class).asEagerSingleton();
		
		/* static injection */
		requestStaticInjection(
			SecurityView.class
		);
	}

}
