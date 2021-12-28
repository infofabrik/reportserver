package net.datenwerke.rs.passwordpolicy.service;

import com.google.inject.Provides;
import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.passwordpolicy.service.lostpassword.LostPasswordModule;
import net.datenwerke.rs.passwordpolicy.service.passwordgenerator.DefaultPasswordGenerator;

public class PasswordPolicyModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "security/passwordpolicy.cf";

   @Override
   protected void configure() {
      bind(PasswordPolicy.class).to(BsiPasswordPolicy.class);
      bind(BsiPasswordPolicyService.class).to(BsiPasswordPolicyServiceImpl.class).in(Singleton.class);
      bind(PasswordPolicyStartup.class).asEagerSingleton();

      install(new LostPasswordModule());
   }

   @Provides
   public PasswordGenerator providePasswordGeneratorProvider(BsiPasswordPolicyService passwordPolicyService) {
      PasswordPolicy pp = passwordPolicyService.getPolicy();
      if (null == pp || null == pp.getPasswordComplexitySpecification())
         return new DefaultPasswordGenerator();

      return pp.getPasswordComplexitySpecification().getPasswordGenerator();
   }

}
