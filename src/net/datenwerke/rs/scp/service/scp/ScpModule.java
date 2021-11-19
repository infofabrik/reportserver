package net.datenwerke.rs.scp.service.scp;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.scp.service.scp.annotations.DefaultScpDatasink;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class ScpModule extends AbstractModule {
   
   private static final String PROPERTY_DATASINK = "scp";
   public static final String PROPERTY_DEFAULT_SCP_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_SCP_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_SCP_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_SCP_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(ScpDatasink.class);

      bind(ScpStartup.class).asEagerSingleton();
   }
   
   @Provides
   @Inject
   @DefaultScpDatasink
   public Optional<ScpDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(ScpDatasink.class, PROPERTY_DEFAULT_SCP_DATASINK_ID,
            PROPERTY_DEFAULT_SCP_DATASINK_NAME);
   }

}
