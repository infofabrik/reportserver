package net.datenwerke.rs.core.service.i18ntools;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.configuration2.Configuration;

import net.datenwerke.gf.service.localization.LocalizationModule;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;

public class I18nToolsServiceImpl implements I18nToolsService {

   private static final String RS_I18N_DEFAULT_DATE_FORMAT = "rs.i18n.defaultdateformat";
   private static final String RS_I18N_DECIMALSEPARATOR_USR_PROP = "i18n:decimalseparator";

   private UserPropertiesService userPropertiesService;
   private Provider<AuthenticatorService> authenticatorServiceProvider;

   private Provider<ApplicationPropertiesService> appPropService;
   private Provider<ConfigService> configService;

   @Inject
   public I18nToolsServiceImpl(UserPropertiesService userPropertiesService,
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<ApplicationPropertiesService> appPropService, Provider<ConfigService> configService) {
      this.userPropertiesService = userPropertiesService;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.appPropService = appPropService;
      this.configService = configService;

   }

   @Override
   public NumberFormat getUserNumberFormatter() {
      try {
         User currentUser = authenticatorServiceProvider.get().getCurrentUser();
         return getUserNumberFormatter(currentUser);
      } catch (AuthenticatorRuntimeException e) {
         return getSystemNumberFormatter();
      }
   }

   @Override
   public NumberFormat getUserNumberFormatter(User user) {
      Locale locale = Locale.ENGLISH;

      if (null != user) {
         if (",".equals(getUserDecimalSeparator(user))) {
            locale = Locale.GERMAN;
         } else if (".".equals(getUserDecimalSeparator(user)))
            locale = Locale.ENGLISH;
      }

      NumberFormat format = NumberFormat.getNumberInstance(locale);
      format.setMaximumFractionDigits(10);
      format.setGroupingUsed(false);

      return format;
   }

   @Override
   public String getUserDecimalSeparator(User u) {
      String sep = userPropertiesService.getPropertyValue(u, RS_I18N_DECIMALSEPARATOR_USR_PROP);

      if (null == sep)
         sep = getSystemDecimalSeparator();

      return sep;
   }

   @Override
   public String getUserDecimalSeparator() {
      try {
         User currentUser = authenticatorServiceProvider.get().getCurrentUser();
         return getUserDecimalSeparator(currentUser);
      } catch (AuthenticatorRuntimeException e) {
         return getSystemDecimalSeparator();
      }
   }

   @Override
   public String getSystemDecimalSeparator() {
      return ".";
   }

   @Override
   public void setUserDecimalSeparator(User u, String separator) {
      userPropertiesService.setPropertyValue(u, RS_I18N_DECIMALSEPARATOR_USR_PROP, separator);
   }

   @Override
   public NumberFormat getSystemNumberFormatter() {
      Locale locale = Locale.ENGLISH;

      NumberFormat format = NumberFormat.getNumberInstance(locale);
      format.setMaximumFractionDigits(10);
      format.setGroupingUsed(false);

      return format;
   }

   @Override
   public String translateNumberFromUserToSystem(String fe) {
      if (null == fe)
         return null;

      return fe.replace(getUserDecimalSeparator(), getSystemDecimalSeparator());
   }

   @Override
   public String translateNumberFromSystemToUser(String fe) {
      if (null == fe)
         return null;

      return fe.replace(getSystemDecimalSeparator(), getUserDecimalSeparator());
   }

   @Override
   public String translateNumberFromSystemToUser(String fe, User user) {
      if (null == fe)
         return null;

      return fe.replace(getSystemDecimalSeparator(), getUserDecimalSeparator(user));
   }

   @Override
   public void validateSystemNumber(String number) {
      if (null != number && !"".equals(number.trim())) {
         try {
            getSystemNumberFormatter().parse(number);
         } catch (ParseException e) {
            throw new IllegalArgumentException("no valid number: " + number, e);
         } catch (NumberFormatException e) {
            throw new IllegalArgumentException("no valid number: " + number, e);
         }
      }
   }

   @Override
   public String getDefaultDateFormat() {
      return appPropService.get().getString(RS_I18N_DEFAULT_DATE_FORMAT, "dd/MM/yyyy");
   }

   @Override
   public FormatPatterns getFormatPatterns() {
      FormatPatterns patterns = new FormatPatterns();
      Configuration cfg = configService.get().getConfigFailsafe(LocalizationModule.CONFIG_FILE);

      patterns.setShortDatePattern(cfg.getString("localization.format.shortDatePattern", null));
      patterns.setLongDatePattern(cfg.getString("localization.format.longDatePattern", null));

      patterns.setShortTimePattern(cfg.getString("localization.format.shortTimePattern", null));
      patterns.setLongTimePattern(cfg.getString("localization.format.longTimePattern", null));

      patterns.setShortDateTimePattern(cfg.getString("localization.format.shortDateTimePattern", null));
      patterns.setLongDateTimePattern(cfg.getString("localization.format.longDateTimePattern", null));

      patterns.setNumberPattern(cfg.getString("localization.format.numberPattern", null));
      patterns.setCurrencyPattern(cfg.getString("localization.format.currencyPattern", null));
      patterns.setIntegerPattern(cfg.getString("localization.format.integerPattern", null));
      patterns.setPercentPattern(cfg.getString("localization.format.percentPattern", null));

      return patterns;
   }

}
