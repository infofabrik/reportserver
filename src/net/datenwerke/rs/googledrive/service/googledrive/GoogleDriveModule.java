package net.datenwerke.rs.googledrive.service.googledrive;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.googledrive.service.googledrive.annotations.DefaultGoogleDriveDatasink;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;

public class GoogleDriveModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "googledrive";
   public static final String PROPERTY_DEFAULT_GOOGLEDRIVE_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_GOOGLEDRIVE_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_GOOGLEDRIVE_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_GOOGLEDRIVE_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(GoogleDriveDatasink.class);

      bind(GoogleDriveStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultGoogleDriveDatasink
   public Optional<GoogleDriveDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(GoogleDriveDatasink.class, PROPERTY_DEFAULT_GOOGLEDRIVE_DATASINK_ID,
            PROPERTY_DEFAULT_GOOGLEDRIVE_DATASINK_NAME);
   }

}
