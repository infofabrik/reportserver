package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public class EmailDatasinkModule extends AbstractModule {

   private static final String PROPERTY_DEFAULT_EMAIL_DATASINK_ID = "email." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_EMAIL_DATASINK_NAME = "email." + PROPERTY_DEFAULT_DATASINK_NAME;

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
   public Optional<EmailDatasink> provideDefaultDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(EmailDatasink.class, PROPERTY_DEFAULT_EMAIL_DATASINK_ID,
            PROPERTY_DEFAULT_EMAIL_DATASINK_NAME);
   }
}