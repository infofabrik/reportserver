package net.datenwerke.gf.service.localization;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.annotations.AvailableLocales;
import net.datenwerke.rs.utils.localization.annotations.CurrentLocale;
import net.datenwerke.rs.utils.localization.annotations.DefaultLocale;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;

/**
 * The localization module offers localization similar to the default GWT i18n
 * implementation, but server side.
 * 
 * <h1>Description</h1>
 * <p>
 * The localization module offers localization similar to the default GWT i18n
 * implementation, but server side.
 * </p>
 * 
 * <h1>Content</h1>
 * <h2>Services</h2>
 * <ul>
 * <li>{@link LocalizationServiceImpl}</li>
 * </ul>
 * 
 * <h2>Static Injections</h2>
 * <ul>
 * <li>{@link LocalizationServiceImpl}</li>
 * </ul>
 * 
 * <h1>Dependencies</h1>
 * 
 * <h2>Entities</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.usermanager.entities.User}</li>
 * </ul>
 * 
 * <h2>3rd-Party</h2>
 * <ul>
 * <li><a href="http://code.google.com/p/google-guice/">Google Guice</a></li>
 * <li><a href="http://cglib.sourceforge.net/">CGLib</a></li>
 * <li><a href="http://code.google.com/intl/de-DE/webtoolkit/">Google
 * GWT</a></li>
 * </ul>
 * 
 * <h2>Others</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.authenticator.AuthenticatorService}</li>
 * </ul>
 */
public class LocalizationModule extends AbstractModule {

   public static final String CONFIG_FILE = "main/localization.cf";
   public static final String USER_LOCAL_PROPERTY_NAME = "localization:locale";

   @Override
   protected void configure() {
      bind(LocalizationStartup.class).asEagerSingleton();

      requestStaticInjection(LocalizationServiceImpl.class);
   }

   @Provides
   @Inject
   @DefaultLocale
   public String provideDefaultLocale(ConfigService configService) {
      return configService.getConfigFailsafe(CONFIG_FILE).getString("localization.default", "en");
   }

   @Provides
   @Inject
   @AvailableLocales
   public Collection<String> provideLocales(ConfigService configService, RemoteMessageService remoteMsgService) {
      String fallback = "en,de";

      /* Check remote message service */
      Collection<String> availableLanguages = remoteMsgService.getAvailableLanguages();
      if (null != availableLanguages && !availableLanguages.isEmpty()) {

         /* Filter on configured available languages. */
         if (configService.getConfigFailsafe(CONFIG_FILE).containsKey("localization.locales")) {
            String cfgVal = configService.getConfigFailsafe(CONFIG_FILE).getString("localization.locales", fallback);
            List<String> configuredLanguages = Arrays.asList(cfgVal.split(","));

            availableLanguages = availableLanguages.stream().filter(lng -> configuredLanguages.contains(lng))
                  .collect(Collectors.toList());
         }
         return availableLanguages;
      }

      /* check config */
      String cfgVal = configService.getConfigFailsafe(CONFIG_FILE).getString("localization.locales", fallback);

      return Arrays.asList(cfgVal.split(","));
   }

   @Provides
   @Inject
   @CurrentLocale
   public String provideDefaultLocale(Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<UserPropertiesService> userPropertiesServiceProvider) {
      try {
         Locale locale = LocalizationServiceImpl.getUserLocal();
         if (null != locale)
            return locale.toLanguageTag();
      } catch (Exception e) {
      }

      return userPropertiesServiceProvider.get().getPropertyValue(authenticatorServiceProvider.get().getCurrentUser(),
            USER_LOCAL_PROPERTY_NAME);
   }
}
