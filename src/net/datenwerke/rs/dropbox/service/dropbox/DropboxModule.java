package net.datenwerke.rs.dropbox.service.dropbox;

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
import net.datenwerke.rs.dropbox.service.dropbox.annotations.DefaultDropboxDatasink;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

public class DropboxModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "dropbox";
   public static final String PROPERTY_DEFAULT_DROPBOX_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_DROPBOX_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_DROPBOX_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_DROPBOX_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_DROPBOX_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(DropboxDatasink.class);

      bind(DropboxStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultDropboxDatasink
   public Optional<DropboxDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(DropboxDatasink.class, PROPERTY_DEFAULT_DROPBOX_DATASINK_ID,
            PROPERTY_DEFAULT_DROPBOX_DATASINK_NAME, PROPERTY_DEFAULT_DROPBOX_DATASINK_KEY);
   }

}
