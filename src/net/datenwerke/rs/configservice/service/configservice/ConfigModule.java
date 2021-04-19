package net.datenwerke.rs.configservice.service.configservice;

import net.datenwerke.rs.utils.config.ConfigService;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ConfigDirService.class).to(ConfigDirServiceImpl.class);
		bind(ConfigService.class).to(ConfigServiceImpl.class).in(Singleton.class);
		
		bind(ConfigStartup.class).asEagerSingleton();
	}

}
