package net.datenwerke.rs.samba.service.samba;

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
import net.datenwerke.rs.samba.service.samba.annotations.DefaultSambaDatasink;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

public class SambaModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "samba";
   public static final String PROPERTY_DEFAULT_SAMBA_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_SAMBA_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_SAMBA_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_SAMBA_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_SAMBA_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(SambaDatasink.class);

      bind(SambaStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultSambaDatasink
   public Optional<SambaDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(SambaDatasink.class, PROPERTY_DEFAULT_SAMBA_DATASINK_ID,
            PROPERTY_DEFAULT_SAMBA_DATASINK_NAME, PROPERTY_DEFAULT_SAMBA_DATASINK_KEY, PROPERTY_SAMBA_DISABLED);
   }

}