package net.datenwerke.rs.dropbox.service.dropbox;

import com.google.inject.AbstractModule;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

public class DropboxModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DropboxService.class).to(DropboxServiceImpl.class);

		requestStaticInjection(DropboxDatasink.class);

		bind(DropboxStartup.class).asEagerSingleton();
	}

}
