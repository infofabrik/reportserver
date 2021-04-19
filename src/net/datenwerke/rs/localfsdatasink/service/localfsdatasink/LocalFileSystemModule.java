package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

public class LocalFileSystemModule extends AbstractModule {

   @Override
   protected void configure() {
      requestStaticInjection(LocalFileSystemDatasink.class);

      bind(LocalFileSystemStartup.class).asEagerSingleton();
   }

}