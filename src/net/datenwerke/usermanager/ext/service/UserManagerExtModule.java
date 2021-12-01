package net.datenwerke.usermanager.ext.service;

import net.datenwerke.usermanager.ext.service.vfs.UserManagerVFSModule;

import com.google.inject.AbstractModule;


public class UserManagerExtModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserManagerExtStartup.class).asEagerSingleton();
		
		/* submodules */

		install(new UserManagerVFSModule());
	}

}
