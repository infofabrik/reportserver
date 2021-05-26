package net.datenwerke.rs.scp.service.scp;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.scp.service.scp.annotations.DefaultScpDatasink;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class ScpModule extends AbstractModule {
   
   private static final String PROPERTY_DEFAULT_SCP_DATASINK_ID = "scp." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_SCP_DATASINK_NAME = "scp." + PROPERTY_DEFAULT_DATASINK_NAME;

   @Override
   protected void configure() {
      bind(ScpService.class).to(ScpServiceImpl.class);
      requestStaticInjection(ScpDatasink.class);

      bind(ScpStartup.class).asEagerSingleton();
   }
   
   @Provides
   @Inject
   @DefaultScpDatasink
   public Optional<ScpDatasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(ScpDatasink.class, PROPERTY_DEFAULT_SCP_DATASINK_ID,
            PROPERTY_DEFAULT_SCP_DATASINK_NAME);
   }

}
