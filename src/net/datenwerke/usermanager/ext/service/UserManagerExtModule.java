package net.datenwerke.usermanager.ext.service;

import com.google.inject.AbstractModule;

import net.datenwerke.usermanager.ext.service.vfs.UserManagerVFSModule;


public class UserManagerExtModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserManagerExtStartup.class).asEagerSingleton();
		
		/* submodules */

		install(new UserManagerVFSModule());
	}

}
