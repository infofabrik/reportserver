package net.datenwerke.rs.configservice.service.manservice;

import net.datenwerke.rs.utils.man.ManPageService;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ManPageModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ManPageService.class).to(ManPageServiceImpl.class).in(Singleton.class);
		bind(ManPageStartup.class).asEagerSingleton();
	}

}
