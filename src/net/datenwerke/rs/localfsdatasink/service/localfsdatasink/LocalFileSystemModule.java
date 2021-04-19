package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

public class LocalFileSystemModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(LocalFileSystemService.class).to(LocalFileSystemServiceImpl.class);
      requestStaticInjection(LocalFileSystemDatasink.class);

      bind(LocalFileSystemStartup.class).asEagerSingleton();
   }

}