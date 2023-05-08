package net.datenwerke.rs.scriptdatasink.service.scriptdatasink;

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
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.annotations.DefaultScriptDatasink;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink;

public class ScriptDatasinkModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "scriptdatasink";
   public static final String PROPERTY_DEFAULT_SCRIPT_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_SCRIPT_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_SCRIPT_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_SCRIPT_DATASINK_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_SCRIPT_DATASINK_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(ScriptDatasink.class);

      bind(ScriptDatasinkStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultScriptDatasink
   public Optional<ScriptDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(ScriptDatasink.class, PROPERTY_DEFAULT_SCRIPT_DATASINK_ID,
            PROPERTY_DEFAULT_SCRIPT_DATASINK_NAME, PROPERTY_DEFAULT_SCRIPT_DATASINK_KEY);
   }

}