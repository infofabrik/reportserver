package net.datenwerke.rs.remoteaccess.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.remoteaccess.service.sftp.SftpModule;

public class RemoteAccessModule extends AbstractModule {

   @Override
   protected void configure() {
      install(new SftpModule());
   }

}
