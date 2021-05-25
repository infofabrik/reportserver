package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.util.Optional;

import org.apache.commons.configuration.Configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.utils.config.ConfigService;

public class EmailDatasinkModule extends AbstractModule {

   public static final String PROPERTY_DEFAULT_DATASINK_ID = "email.defaultEmailDatasinkId";
   public static final String PROPERTY_DEFAULT_DATASINK_NAME = "email.defaultEmailDatasinkName";

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
   public Optional<EmailDatasink> provideDefaultEmailDatasinkId(DatasinkService datasinkService,
         ConfigService configService) {
      Configuration config = configService.getConfigFailsafe(DatasinkModule.CONFIG_FILE);

      if (config.containsKey(PROPERTY_DEFAULT_DATASINK_ID)) {
         Long id = config.getLong(PROPERTY_DEFAULT_DATASINK_ID);
         DatasinkDefinition datasink = datasinkService.getDatasinkById(id);
         if (null != datasink && datasink instanceof EmailDatasink)
            return Optional.of((EmailDatasink) datasink);
      } else {
         if (config.containsKey(PROPERTY_DEFAULT_DATASINK_NAME)) {
            String name = config.getString(PROPERTY_DEFAULT_DATASINK_NAME);
            DatasinkDefinition datasink = datasinkService.getDatasinkByName(name);
            if (null != datasink && datasink instanceof EmailDatasink)
               return Optional.of((EmailDatasink) datasink);
         }
      }
      return Optional.empty();
   }
}