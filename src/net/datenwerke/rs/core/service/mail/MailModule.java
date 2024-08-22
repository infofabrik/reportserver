package net.datenwerke.rs.core.service.mail;

import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class MailModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      /* bind service */
      bind(MailService.class).to(MailServiceImpl.class).in(Scopes.SINGLETON);

      install(new FactoryModuleBuilder().build(MailBuilderFactory.class));
      install(new FactoryModuleBuilder().build(SimpleCryptoMailFactory.class));
      install(new FactoryModuleBuilder().build(SimpleMailFactory.class));
   }
}
