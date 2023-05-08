package net.datenwerke.rs.tabledatasink.service.tabledatasink;

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
import net.datenwerke.rs.tabledatasink.service.tabledatasink.annotations.DefaultTableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;

public class TableDatasinkModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "tabledatasink";
   public static final String PROPERTY_DEFAULT_TABLE_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_TABLE_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_TABLE_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_TABLE_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_TABLE_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(TableDatasink.class);

      bind(TableDatasinkStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultTableDatasink
   public Optional<TableDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(TableDatasink.class, PROPERTY_DEFAULT_TABLE_DATASINK_ID,
            PROPERTY_DEFAULT_TABLE_DATASINK_NAME, PROPERTY_DEFAULT_TABLE_DATASINK_KEY);
   }

}
