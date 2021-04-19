package net.datenwerke.usermanager.ext.service.vfs;

import com.google.inject.AbstractModule;


public class UserManagerVFSModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserManagerVFSStartup.class).asEagerSingleton();
	}

}
