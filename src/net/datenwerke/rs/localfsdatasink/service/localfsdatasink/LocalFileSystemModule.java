package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.annotations.DefaultLocalFileSystemDatasink;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

public class LocalFileSystemModule extends AbstractModule {

   private static final String PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_ID = "localfilesystem."
         + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_NAME = "localfilesystem."
         + PROPERTY_DEFAULT_DATASINK_NAME;

   @Override
   protected void configure() {
      bind(LocalFileSystemService.class).to(LocalFileSystemServiceImpl.class);
      requestStaticInjection(LocalFileSystemDatasink.class);

      bind(LocalFileSystemStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultLocalFileSystemDatasink
   public Optional<LocalFileSystemDatasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(LocalFileSystemDatasink.class,
            PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_ID, PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_NAME);
   }

}