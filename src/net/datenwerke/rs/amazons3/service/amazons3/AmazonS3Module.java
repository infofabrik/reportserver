package net.datenwerke.rs.amazons3.service.amazons3;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.amazons3.service.amazons3.annotations.DefaultAmazonS3Datasink;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;

public class AmazonS3Module extends AbstractModule {

   private static final String PROPERTY_DATASINK = "amazons3";
   public static final String PROPERTY_DEFAULT_AMAZONS3_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_AMAZONS3_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_AMAZONS3_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_AMAZONS3_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(AmazonS3Datasink.class);

      bind(AmazonS3Startup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultAmazonS3Datasink
   public Optional<AmazonS3Datasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(AmazonS3Datasink.class, PROPERTY_DEFAULT_AMAZONS3_DATASINK_ID,
            PROPERTY_DEFAULT_AMAZONS3_DATASINK_NAME);
   }

}