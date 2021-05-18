package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public class EmailDatasinkModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(EmailDatasinkService.class).to(EmailDatasinkServiceImpl.class);

      requestStaticInjection(EmailDatasink.class);

      bind(EmailDatasinkStartup.class).asEagerSingleton();
      
      install(new FactoryModuleBuilder().build(EmailDatasinkSessionFactory.class));
   }

}