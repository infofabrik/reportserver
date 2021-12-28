package net.datenwerke.rs.fileserver.service.fileserver;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.fileserver.service.fileserver.genrights.GenRightsFileServerManagerModule;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfig;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfigFactory;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfsModule;

public class FileServerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(FileServerService.class).to(FileServerServiceImpl.class).in(Singleton.class);
      bind(FileServerStartup.class).asEagerSingleton();

      install(new FactoryModuleBuilder().implement(BasepathZipExtractConfig.class, BasepathZipExtractConfig.class)
            .build(BasepathZipExtractConfigFactory.class));

      install(new FileServerVfsModule());
      install(new GenRightsFileServerManagerModule());
   }

}
