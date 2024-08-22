package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_KEY;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public class EmailDatasinkModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "email";
   public static final String PROPERTY_DEFAULT_EMAIL_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_EMAIL_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_EMAIL_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_EMAIL_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_EMAIL_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      bind(EmailDatasinkService.class).to(EmailDatasinkServiceImpl.class);

      requestStaticInjection(EmailDatasink.class);

      bind(EmailDatasinkStartup.class).asEagerSingleton();

      install(new FactoryModuleBuilder().build(EmailDatasinkSessionFactory.class));
   }

   @Provides
   @Inject
   @DefaultEmailDatasink
   public Optional<EmailDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(EmailDatasink.class, PROPERTY_DEFAULT_EMAIL_DATASINK_ID,
            PROPERTY_DEFAULT_EMAIL_DATASINK_NAME, PROPERTY_DEFAULT_EMAIL_DATASINK_KEY, PROPERTY_EMAIL_DISABLED);
   }
}