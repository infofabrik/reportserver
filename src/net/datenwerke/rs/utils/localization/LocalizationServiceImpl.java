package net.datenwerke.rs.utils.localization;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.localization.annotations.AvailableLocales;
import net.datenwerke.rs.utils.localization.annotations.CurrentLocale;
import net.datenwerke.rs.utils.localization.annotations.DefaultLocale;
import net.datenwerke.rs.utils.localization.hooks.LocaleChangedNotificationHook;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Offers localization similar to the default GWT i18n implementation, 
 * but on the serverside. 
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
	
	/**
	 * Caches instanzes of Messages 
	 */
	private static HashMap<Class<? extends Messages>, Messages> cache = new HashMap<Class<? extends Messages>, Messages>();

	/**
	 * Retrieves (and creates) an implementation of the supplied Message interface, 
	 * where all methods are implemented to return the values speciefied in the current
	 * locales propterty file
	 */
	public static <T extends Messages> T getMessages(Class<T> msgInterface) {
		if(!cache.containsKey(msgInterface))
			cache.put(msgInterface, createMessages(msgInterface, null));
		
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
		if(!cache.containsKey(msgInterface))
			cache.put(msgInterface, createMessages(msgInterface, null));
		
		T msgInstance = (T) cache.get(msgInterface);
		
		try {
			return (String) msgInstance.getClass().getMethod(msg).invoke(msgInstance);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		
		return "";
	}

	/**
	 * Dynamically create an implementation of the supplied interface which retrieves the
	 * return values of all methods retuning type of String from a map
	 * 
	 * @param <T>
	 * @param msgInterface
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Messages> T createMessages(final Class<T> msgInterface, final Map<String, ? extends Map> xmappings) {
		/* default */
		Enhancer enhancer = new Enhancer();
		enhancer.setInterfaces(new Class[]{msgInterface, Messages.class});
		enhancer.setCallback(new MethodInterceptor() {

			private Map<String, ? extends Map> mappings = xmappings;
			
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				
				/* load mapping */
				if(null == mappings)
					mappings = getMappings(msgInterface);
				
				if (!Modifier.isAbstract(method.getModifiers()))
                    return proxy.invokeSuper(proxy, args);
                else {
                    Class<?> type = method.getReturnType();
                    String key = method.getName();

                    /* try to find locale */
                    String locale = DEFAULT_KEY.get();
                    try{
                    	/* override with different local */
                    	String userLocale = currentLocaleProvider.get();
                    	if(null != userLocale && ! "".equals(userLocale))
                    		locale = userLocale;
                    } catch(Exception e){
                    	// swallow
                    }
                    
                    if (type.equals(String.class)) {
                        if(args.length == 0){
                        	return getMapping(mappings, locale, key, obj);
                        }else{
                        	String template = getMapping(mappings, locale, key, obj);
                        	return MessageFormat.format(template, args);
                        }
                    } else {
                        return null;
                    }
                }
			}
		});
		
		return (T) enhancer.create();
	}
	

	protected static String getMapping(Map<String, ? extends Map> mappings,
			String locale, String key, Object obj) {
		if(! mappings.containsKey(locale))
			if(locale.equals(FALLBACK_KEY))
				return obj.getClass().getSimpleName() + "." + key;
			else
				return getMapping(mappings, FALLBACK_KEY, key, obj);
		
		if(! mappings.get(locale).containsKey(key) )
			if(locale.equals(FALLBACK_KEY))
				return obj.getClass().getSimpleName() + "." + key;
			else
				return getMapping(mappings, FALLBACK_KEY, key, obj);
		
		return (String) mappings.get(locale).get(key);
	}


	/**
	 * Loads a property file with a name derived from the given interface
	 * and returns a map of all of its entries
	 * 
	 * @param msgInterface
	 */
	protected static Map<String, Properties> getMappings(Class<?> msgInterface){
		/* load mapping */
		Map<String, Properties> mapping = new HashMap<String, Properties>();
		
		/* add locales */
		for(String locale : getAvailableLocales())
			addToMapping(msgInterface, locale, mapping);
		
		return mapping;
	}
	
	private static void addToMapping(Class<?> msgInterface, String locale, Map<String, Properties> mapping) {
		String propfileBasename = 
			msgInterface.getSimpleName() + ((null == locale || "".equals(locale)) ? "" : "_") + locale + ".properties"; //$NON-NLS-1$
		
		Properties props = new Properties();
		try {
			InputStream stream = msgInterface.getResourceAsStream(propfileBasename);
			if(null != stream){
				props.load(msgInterface.getResourceAsStream(propfileBasename));
				mapping.put(locale, props);
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(LocalizationServiceImpl.class.getName()).warn("error generating key", e);
		}
	}
	

	public static Collection<String> getAvailableLocales() {
		Collection<String> locales = new LinkedHashSet<String>();
		
		for(String localeString : availableLocales.get())
			locales.add(localeString.trim());
		
		/* add fallback */
		if (locales.isEmpty())
			locales.add(FALLBACK_KEY);
		
		return locales;
	}
	
	public static Locale getLocale(){
		 String locale = DEFAULT_KEY.get();
         try{
         	/* override with different local */
         	String userLocale = null;
         	try{ 
         		userLocale = currentLocaleProvider.get();
         	}catch(Exception e){
         		
         		// exception if no user logged in
         	}
         	if(null != userLocale && ! "".equals(userLocale))
         		locale = userLocale;
         	
         	return Locale.forLanguageTag(locale);
         } catch(Exception e){
         	// swallow
         }
         
         return Locale.ENGLISH;
	}
	
	
	/**
	 * Internal use only. Always use getLocale()
	 * Returns the raw(!) locale send back by the client
	 */
	public static Locale getUserLocal(){
		return userLocale.get().getLocale();
	}
	
	public static void setUserLocal(Locale locale){
		userLocale.get().setLocale(locale);
		
		for(LocaleChangedNotificationHook hooker : hookHandlerServiceProvider.get().getHookers(LocaleChangedNotificationHook.class))
			hooker.localeChanged(locale);
	}

	public static void setUserTimezone(String timezone) {
		userLocale.get().setUserTimezone(timezone);
	}
	
	public String getUserTimezone(){
		return userLocale.get().getUserTimezone();
	}


}
