package net.datenwerke.rs.onedrive.service.onedrive;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import net.datenwerke.rs.onedrive.service.onedrive.annotations.DefaultOneDriveDatasink;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

public class OneDriveModule extends AbstractModule {
   private static final String PROPERTY_DATASINK = "onedrive";
   public static final String PROPERTY_DEFAULT_ONEDRIVE_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_ONEDRIVE_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_ONEDRIVE_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_ONEDRIVE_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(OneDriveDatasink.class);

      bind(OneDriveStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultOneDriveDatasink
   public Optional<OneDriveDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(OneDriveDatasink.class, PROPERTY_DEFAULT_ONEDRIVE_DATASINK_ID,
            PROPERTY_DEFAULT_ONEDRIVE_DATASINK_NAME);
   }
}