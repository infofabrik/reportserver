package net.datenwerke.rs.samba.service.samba;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.samba.service.samba.annotations.DefaultSambaDatasink;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

public class SambaModule extends AbstractModule {

   private static final String PROPERTY_DEFAULT_SAMBA_DATASINK_ID = "samba." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_SAMBA_DATASINK_NAME = "samba." + PROPERTY_DEFAULT_DATASINK_NAME;

   @Override
   protected void configure() {
      bind(SambaService.class).to(SambaServiceImpl.class);
      requestStaticInjection(SambaDatasink.class);

      bind(SambaStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultSambaDatasink
   public Optional<SambaDatasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(SambaDatasink.class, PROPERTY_DEFAULT_SAMBA_DATASINK_ID,
            PROPERTY_DEFAULT_SAMBA_DATASINK_NAME);
   }

}