package net.datenwerke.rs.utils.localization;

import static net.bytebuddy.matcher.ElementMatchers.isAbstract;
import static net.bytebuddy.matcher.ElementMatchers.returns;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.utils.localization.annotations.AvailableLocales;
import net.datenwerke.rs.utils.localization.annotations.CurrentLocale;
import net.datenwerke.rs.utils.localization.annotations.DefaultLocale;
import net.datenwerke.rs.utils.localization.hooks.LocaleChangedNotificationHook;

/**
 * Offers localization similar to the default GWT i18n implementation, but on
 * the serverside.
 * 
 *
 */
public class LocalizationServiceImpl implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 2696620936401833977L;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final String FALLBACK_KEY = "en";

   @Inject
   private static Provider<HookHandlerService> hookHandlerServiceProvider;

   @Inject
   private static @DefaultLocale Provider<String> DEFAULT_KEY;

   @Inject
   private static @AvailableLocales Provider<Collection<String>> availableLocales;

   @Inject
   private static @CurrentLocale Provider<String> currentLocaleProvider;

   @Inject
   private static Provider<UserLocale> userLocale;
   
   @Inject
   private static Provider<I18nToolsService> i18nToolsServiceProvider;

   /**
    * Caches instances of Messages
    */
   private static HashMap<Class<? extends Messages>, Messages> cache = new HashMap<Class<? extends Messages>, Messages>();

   /**
    * Retrieves (and creates) an implementation of the supplied Message interface,
    * where all methods are implemented to return the values specified in the
    * current locales property file
    */
   public static <T extends Messages> T getMessages(Class<T> msgInterface) {
      if (!cache.containsKey(msgInterface))
         try {
            cache.put(msgInterface, createMessages(msgInterface, null));
         } catch (InstantiationException | IllegalAccessException e) {
         }

      return (T) cache.get(msgInterface);
   }

   public static <T extends Messages> Provider<T> getMessagesProvider(final Class<T> msgInterface) {
      return new Provider<T>() {
         @Override
         public T get() {
            return getMessages(msgInterface);
         }
      };
   }

   public static <T extends Messages> String getMessage(Class<T> msgInterface, String msg) {
      try {
         if (!cache.containsKey(msgInterface))
            cache.put(msgInterface, createMessages(msgInterface, null));

         T msgInstance = (T) cache.get(msgInterface);
         return (String) msgInstance.getClass().getMethod(msg).invoke(msgInstance);
      } catch (SecurityException e) {
      } catch (NoSuchMethodException e) {
      } catch (IllegalArgumentException e) {
      } catch (IllegalAccessException e) {
      } catch (InvocationTargetException e) {
      } catch (InstantiationException e) {
      }

      return "";
   }

   /**
    * Dynamically create an implementation of the supplied interface which
    * retrieves the return values of all methods retuning type of String from a map
    * 
    * @param <T>
    * @param msgInterface
    * @throws IllegalAccessException 
    * @throws InstantiationException 
    */
   @SuppressWarnings("unchecked")
   public static <T extends Messages> T createMessages(final Class<T> msgInterface,
         final Map<String, ? extends Map> xmappings) throws InstantiationException, IllegalAccessException {
      Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .implement(msgInterface)
            .method(isAbstract()
                  .and(returns(String.class)))
            .intercept(MethodDelegation.to(new LocalizationInterceptor(xmappings)))
            .make()
            .load(LocalizationServiceImpl.class.getClassLoader())
            .getLoaded();
           
      return (T) dynamicType.newInstance();
   }

   protected static String getMapping(Map<String, ? extends Map> mappings, String locale, String key, Object obj) {
      if (!mappings.containsKey(locale))
         if (locale.equals(FALLBACK_KEY))
            return obj.getClass().getSimpleName() + "." + key;
         else
            return getMapping(mappings, FALLBACK_KEY, key, obj);

      if (!mappings.get(locale).containsKey(key))
         if (locale.equals(FALLBACK_KEY))
            return obj.getClass().getSimpleName() + "." + key;
         else
            return getMapping(mappings, FALLBACK_KEY, key, obj);

      return (String) mappings.get(locale).get(key);
   }

   /**
    * Loads a property file with a name derived from the given interface and
    * returns a map of all of its entries
    * 
    * @param msgInterface
    */
   protected static Map<String, Properties> getMappings(final Class<?> msgInterface) {
      /* load mapping */
      final Map<String, Properties> mapping = new HashMap<>();

      /* add locales */
      getAvailableLocales()
         .forEach(locale -> addToMapping(msgInterface, locale, mapping));

      return mapping;
   }

   private static void addToMapping(Class<?> msgInterface, String locale, Map<String, Properties> mapping) {
      String propfileBasename = msgInterface.getSimpleName() + ((null == locale || "".equals(locale)) ? "" : "_") //$NON-NLS-1$
            + locale + ".properties";

      Properties props = new Properties();
      try {
         InputStream stream = msgInterface.getResourceAsStream(propfileBasename);
         if (null != stream) {
            props.load(msgInterface.getResourceAsStream(propfileBasename));
            mapping.put(locale, props);
         }
      } catch (Exception e) {
         LoggerFactory.getLogger(LocalizationServiceImpl.class.getName()).warn("error generating key", e);
      }
   }

   public static Collection<String> getAvailableLocales() {
      Collection<String> locales = new LinkedHashSet<String>();

      for (String localeString : availableLocales.get())
         locales.add(localeString.trim());

      /* add fallback */
      if (locales.isEmpty())
         locales.add(FALLBACK_KEY);

      return locales;
   }

   public static Locale getLocale() {
      String locale = DEFAULT_KEY.get();
      try {
         /* override with different local */
         String userLocale = null;
         try {
            userLocale = currentLocaleProvider.get();
         } catch (Exception e) {

            // exception if no user logged in
         }
         if (null != userLocale && !"".equals(userLocale))
            locale = userLocale;

      } catch (Exception e) {
         // swallow
      }

      return new Locale(locale, i18nToolsServiceProvider.get().getRegion());
   }

   /**
    * Internal use only. Always use getLocale() Returns the raw(!) locale send back
    * by the client
    */
   public static Locale getUserLocal() {
      return userLocale.get().getLocale();
   }

   public static void setUserLocal(Locale locale) {
      userLocale.get().setLocale(locale);

      for (LocaleChangedNotificationHook hooker : hookHandlerServiceProvider.get()
            .getHookers(LocaleChangedNotificationHook.class))
         hooker.localeChanged(locale);
   }

   public static void setUserTimezone(String timezone) {
      userLocale.get().setUserTimezone(timezone);
   }

   public String getUserTimezone() {
      return userLocale.get().getUserTimezone();
   }
   
}
