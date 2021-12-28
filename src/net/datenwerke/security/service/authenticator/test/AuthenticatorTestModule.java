package net.datenwerke.security.service.authenticator.test;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

public class AuthenticatorTestModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(AuthenticatorService.class).to(AuthenticatorTestServiceImpl.class).in(Singleton.class);

      Multibinder<ReportServerPAM> pamBinder = Multibinder.newSetBinder(binder(), ReportServerPAM.class);
   }

}
