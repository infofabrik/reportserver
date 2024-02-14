package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_KEY;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.annotations.DefaultLocalFileSystemDatasink;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

public class LocalFileSystemModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "localfilesystem";
   public static final String PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_LOCAL_FILESYSTEM_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_LOCAL_FILESYSTEM_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(LocalFileSystemDatasink.class);

      bind(LocalFileSystemStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultLocalFileSystemDatasink
   public Optional<LocalFileSystemDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(LocalFileSystemDatasink.class,
            PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_ID, PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_NAME,
            PROPERTY_DEFAULT_LOCAL_FILESYSTEM_DATASINK_KEY, PROPERTY_LOCAL_FILESYSTEM_DISABLED);
   }

}