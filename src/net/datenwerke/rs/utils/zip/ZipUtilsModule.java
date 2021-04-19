package net.datenwerke.rs.utils.zip;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class ZipUtilsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ZipUtilsService.class).to(ZipUtilsServiceImpl.class).in(Scopes.SINGLETON);
	}

}
