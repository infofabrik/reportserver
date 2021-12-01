package net.datenwerke.rs.remoteaccess.service;

import net.datenwerke.rs.remoteaccess.service.sftp.SftpModule;

import com.google.inject.AbstractModule;

public class RemoteAccessModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new SftpModule());
	}
	
	
}
