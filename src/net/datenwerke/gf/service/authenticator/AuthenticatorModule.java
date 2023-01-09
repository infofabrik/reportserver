package net.datenwerke.gf.service.authenticator;

import static java.util.stream.Collectors.toCollection;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletScopes;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.authenticator.AuthenticatorServiceImpl;
import net.datenwerke.security.service.authenticator.CurrentUser;
import net.datenwerke.security.service.authenticator.ReportServerPAM;
import net.datenwerke.security.service.authenticator.RequestUserCache;
import net.datenwerke.security.service.authenticator.RequestUserCacheImpl;
import net.datenwerke.security.service.authenticator.hooks.PAMHook;

/**
 *
 */
public class AuthenticatorModule extends AbstractModule {

   public static final String AUTHENTICATORS_PROPERTY_NAME = "rs.authenticator.pams";
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Override
   protected void configure() {
      bind(CurrentUser.class).in(ServletScopes.SESSION);
      bind(AuthenticatorService.class).to(AuthenticatorServiceImpl.class);
      bind(RequestUserCache.class).to(RequestUserCacheImpl.class).in(ServletScopes.REQUEST);
   }

   @Inject
   @Provides
   public Set<ReportServerPAM> providePAMs(final ApplicationPropertiesService propertiesService,
         final HookHandlerService hookHandlerService, final Injector injector) {
      String authenticatorsString = propertiesService.getString(AUTHENTICATORS_PROPERTY_NAME);

      hookHandlerService.getHookers(PAMHook.class).forEach(PAMHook::beforeStaticPamConfig);

      final LinkedHashSet<ReportServerPAM> pams = Arrays.stream(authenticatorsString.split(":"))
         .filter(className -> !className.trim().isEmpty())
         .map(className -> {
            ReportServerPAM pamInstance = null;
            try {
               Class<?> pamClass = Class.forName(className);
               pamInstance = (ReportServerPAM) injector.getInstance(pamClass);
            } catch (ClassNotFoundException e) {
               logger.warn("Failed loading pam", e);
            }
            return pamInstance;
         })
         .collect(toCollection(LinkedHashSet::new));
         
      hookHandlerService.getHookers(PAMHook.class).forEach(ph -> ph.afterStaticPamConfig(pams));
      return pams;
         
   }
}
