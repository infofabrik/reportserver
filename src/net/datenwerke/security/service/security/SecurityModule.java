package net.datenwerke.security.service.security;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

import net.datenwerke.rs.utils.guice.GuiceMatchers;
import net.datenwerke.security.service.genrights.security.GenRightsSecurityModule;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.annotation.UpdateOwner;
import net.datenwerke.security.service.security.aop.SecurityCheckInterceptor;
import net.datenwerke.security.service.security.aop.UpdateOwnerInformationInterceptor;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;

/**
 * 
 *
 */
public class SecurityModule extends AbstractModule {

   public static final String CONFIG_FILE = "security/misc.cf";
   public static final String ERROR_MESSAGE_LEVEL_PROPERTY = "errorMessages.hideViolatedSecurityExceptionDetails";

   @Override
   protected void configure() {
      /* bind services */
      bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);

      /* bind security interceptor */
      SecurityCheckInterceptor securityCheckedInterceptor = new SecurityCheckInterceptor();
      requestInjection(securityCheckedInterceptor);
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(SecurityChecked.class), securityCheckedInterceptor);
      bindInterceptor(Matchers.annotatedWith(SecurityChecked.class),
            Matchers.not(Matchers.annotatedWith(SecurityChecked.class)).and(GuiceMatchers.publicMethod()),
            securityCheckedInterceptor);

      /* bind update owner interceptor */
      UpdateOwnerInformationInterceptor updateOwnerInterceptor = new UpdateOwnerInformationInterceptor();
      requestInjection(updateOwnerInterceptor);
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(UpdateOwner.class), updateOwnerInterceptor);

      /* startup */
      bind(SecurityStartup.class).asEagerSingleton();

      /* submodules */
      install(new GenRightsSecurityModule());

      requestStaticInjection(ViolatedSecurityException.class);
   }

}
