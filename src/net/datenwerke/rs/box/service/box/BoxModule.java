package net.datenwerke.rs.box.service.box;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_KEY;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.box.service.box.annotations.DefaultBoxDatasink;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;

public class BoxModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "box";
   public static final String PROPERTY_DEFAULT_BOX_DATASINK_ID = PROPERTY_DATASINK + "." + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_BOX_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_BOX_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_BOX_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_BOX_SCHEDULING_ENABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(BoxDatasink.class);

      bind(BoxStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultBoxDatasink
   public Optional<BoxDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(BoxDatasink.class, PROPERTY_DEFAULT_BOX_DATASINK_ID,
            PROPERTY_DEFAULT_BOX_DATASINK_NAME, PROPERTY_DEFAULT_BOX_DATASINK_KEY);
   }

}
