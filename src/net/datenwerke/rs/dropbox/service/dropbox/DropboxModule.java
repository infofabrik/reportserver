package net.datenwerke.rs.dropbox.service.dropbox;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.dropbox.service.dropbox.annotations.DefaultDropboxDatasink;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

public class DropboxModule extends AbstractModule {

   private static final String PROPERTY_DEFAULT_DROPBOX_DATASINK_ID = "dropbox." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_DROPBOX_DATASINK_NAME = "dropbox." + PROPERTY_DEFAULT_DATASINK_NAME;

   @Override
   protected void configure() {
      bind(DropboxService.class).to(DropboxServiceImpl.class);

      requestStaticInjection(DropboxDatasink.class);

      bind(DropboxStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultDropboxDatasink
   public Optional<DropboxDatasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(DropboxDatasink.class, PROPERTY_DEFAULT_DROPBOX_DATASINK_ID,
            PROPERTY_DEFAULT_DROPBOX_DATASINK_NAME);
   }

}
