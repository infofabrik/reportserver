package net.datenwerke.rs.core.service.i18ntools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.gxtdto.client.i18n.remotemessages.DwRemoteMessageOpts;
import net.datenwerke.gxtdto.client.i18n.remotemessages.DwRemoteMessageOverride;
import net.datenwerke.gxtdto.client.i18n.remotemessages.rpc.RemoteMessageRpcService;
import net.datenwerke.rs.core.client.locale.ReportServerMessages;
import net.datenwerke.rs.utils.localization.annotations.DefaultLocale;

@Singleton
public class RemoteMessageServiceImpl extends RemoteServiceServlet
      implements RemoteMessageRpcService, RemoteMessageService {

   private final static long serialVersionUID = -7729138642728643073L;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   private final Provider<String> defaultLocale;
   private final Pattern msgPropPatterns[] = {
         Pattern.compile(".*(net[/\\\\]datenwerke[/\\\\].*[/\\\\]locale[/\\\\].*Messages)\\_(.+)\\.properties$"),
         Pattern.compile(
               ".*(com[/\\\\]sencha[/\\\\]gxt[/\\\\]messages[/\\\\]client[/\\\\]XMessages)\\_(.+)\\.properties$"),
         Pattern.compile(
               ".*(com[/\\\\]sencha[/\\\\]gxt[/\\\\]messages[/\\\\]client[/\\\\]XMessages)(.*)\\.properties$") };

   // lang -> file -> key -> val
   private HashMap<String, HashMap<String, HashMap<String, String>>> cache = new HashMap<>();
   private HashMap<String, HashMap<String, HashMap<String, String>>> overrides = new HashMap<>();

   @Inject
   public RemoteMessageServiceImpl(@DefaultLocale Provider<String> defaultLocale) {
      this.defaultLocale = defaultLocale;
      logger.info("Loading messages... ");
      loadMessageFiles();
      logger.info("Available locales: " + StringUtils.join(cache.keySet(), ", "));
   }

   @Override
   public HashMap<String, HashMap<String, String>> getMessages(String language) {
      if (null == language || language.isEmpty())
         language = defaultLocale.get();

      HashMap<String, HashMap<String, String>> map = cache.get(language);

      if (null == map || map.isEmpty())
         return cache.get(defaultLocale.get());

      return map;
   }

   @Override
   public Collection<String> getAvailableLanguages() {
      HashSet<String> lngs = new HashSet<String>();
      lngs.addAll(cache.keySet());
      lngs.remove("keys");
      return lngs;
   }

   private void loadMessageFiles() {
      HashSet<URL> urls = new HashSet<>();
      urls.addAll(getClasspathURLs());
      urls.addAll(getClasspathURLs(ClassLoader.getSystemClassLoader()));

      for (URL u : urls) {
         try {
            File f = new File(u.toURI());
            if (f.isDirectory()) {
               findInFolder(f);
            }
         } catch (URISyntaxException e) {
            logger.warn("Could not load url for parsing message files " + String.valueOf(u) + ".", e);
         } catch (IOException e) {
            logger.warn("Could not load url for parsing message files " + String.valueOf(u) + ".", e);
         }
         try {
            findInJar(u);
         } catch (IOException e) {
            logger.warn("Could not load jar for parsing message files " + String.valueOf(u) + ".", e);
         }
      }
      // set missing keys from default NOLANG.properties
      HashMap<String, HashMap<String, String>> defaultCache = cache.get("");
      for (String language : cache.keySet()) {
         if ("".equals(language))
            continue;
         for (String defaultFile : defaultCache.keySet()) {
            HashMap<String, String> defaultMap = defaultCache.get(defaultFile);
            HashMap<String, String> targetFile = cache.get(language).get(defaultFile);
            if (null != targetFile) {
               for (String key : defaultMap.keySet()) {
                  if (!targetFile.containsKey(key)) {
                     targetFile.put(key, defaultMap.get(key));
                  }
               }
            } else
               cache.get(language).put(defaultFile, new HashMap<String, String>(defaultMap));
         }
      }

      /* integrate overrides */
      for (String language : overrides.keySet()) {
         for (String file : overrides.get(language).keySet()) {
            cache.get(language).get(file).putAll(overrides.get(language).get(file));
         }
      }

      /* remove all languages that have no instance of ReportServerMessage */
      ArrayList<String> removeKeys = new ArrayList<>();
      for (String key : cache.keySet()) {
         if (!cache.get(key).containsKey(ReportServerMessages.class.getName())) {
            removeKeys.add(key);
         }
      }
      logger.debug("remove locale stubs: " + StringUtils.join(removeKeys, ", "));
      for (String key : removeKeys) {
         cache.remove(key);
      }

      /* create the "key" language */
      HashMap<String, HashMap<String, String>> keyLang = new HashMap<String, HashMap<String, String>>();
      for (String file : cache.get("de").keySet()) {
         for (String key : cache.get("de").get(file).keySet()) {
            addTranslation("keys", file, key, key + "::" + file, false);
         }
      }
   }

   private void findInFolder(File parent) throws IOException {
      Collection<File> files = FileUtils.listFiles(parent, null, true);
      for (File f : files) {
         for (Pattern msgPropPattern : msgPropPatterns) {
            Matcher matcher = msgPropPattern.matcher(f.getCanonicalPath());
            if (matcher.matches()) {
               try (FileInputStream fis = new FileInputStream(f)) {
                  processPropfile(matcher.group(2), matcher.group(1).replaceAll("[/\\\\]", "."), fis);
                  break;
               }
            }
         }
      }
   }

   private void findInJar(URL jarUrl) throws IOException {
      try (JarInputStream jar = new JarInputStream(jarUrl.openStream())) {
         JarEntry entry;
         while (null != (entry = jar.getNextJarEntry())) {
            for (Pattern msgPropPattern : msgPropPatterns) {
               Matcher matcher = msgPropPattern.matcher(entry.getName());
               if (matcher.matches()) {
                  processPropfile(matcher.group(2), matcher.group(1).replaceAll("[/\\\\]", "."), jar);
                  break;
               }
            }
         }
      }
   }

   private HashMap<String, DwRemoteMessageOpts> getMethodAnnotations(String msgClass) {
      HashMap<String, DwRemoteMessageOpts> res = new HashMap<String, DwRemoteMessageOpts>();
      try {
         Class msgIface = Class.forName(msgClass, false, getClass().getClassLoader());
         for (Method m : msgIface.getMethods()) {
            DwRemoteMessageOpts annotation = m.getAnnotation(DwRemoteMessageOpts.class);
            if (null != annotation)
               res.put(m.getName(), annotation);
         }

      } catch (ClassNotFoundException e) {
      }

      return res;
   }

   private DwRemoteMessageOverride getOverrideAnnotation(String msgClass) {
      try {
         Class msgIface = Class.forName(msgClass, false, getClass().getClassLoader());
         return (DwRemoteMessageOverride) msgIface.getAnnotation(DwRemoteMessageOverride.class);
      } catch (ClassNotFoundException e) {
      }
      return null;
   }

   private void processPropfile(String language, String msgClass, InputStream is) throws IOException {
      HashMap<String, DwRemoteMessageOpts> optsmap = getMethodAnnotations(msgClass);
      DwRemoteMessageOverride overrideAnnotation = getOverrideAnnotation(msgClass);

      Properties propFile = new Properties();
      propFile.load(new InputStreamReader(is, "ISO-8859-1"));
      for (Object key : propFile.keySet()) {
         String sKey = String.valueOf(key);
         String val = propFile.getProperty(sKey);

         DwRemoteMessageOpts opts = optsmap.get(sKey);
         if (null == opts || opts.unescape()) {
            val = val.replaceAll("''", "'");
         }

         if (null != overrideAnnotation)
            msgClass = overrideAnnotation.value().getName();

         addTranslation(language, msgClass, sKey, val, null != overrideAnnotation);
      }
   }

   @Override
   public void addTranslation(String lang, String msgClass, String key, String value, boolean override) {
      if (!cache.containsKey(lang))
         cache.put(lang, new HashMap<String, HashMap<String, String>>());

      HashMap<String, HashMap<String, String>> files = cache.get(lang);
      if (!files.containsKey(msgClass))
         files.put(msgClass, new HashMap<String, String>());

      HashMap<String, String> keys = files.get(msgClass);
      logger.debug(lang + " " + msgClass + " " + key);
      keys.put(key, value);

      if (override) {
         if (!overrides.containsKey(lang))
            overrides.put(lang, new HashMap<String, HashMap<String, String>>());

         HashMap<String, HashMap<String, String>> ofiles = overrides.get(lang);
         if (!ofiles.containsKey(msgClass))
            ofiles.put(msgClass, new HashMap<String, String>());

         HashMap<String, String> okeys = ofiles.get(msgClass);
         logger.debug(lang + " " + msgClass + " " + key);
         okeys.put(key, value);
      }
   }

   private Collection<URL> getClasspathURLs() {
      return getClasspathURLs(this.getClass().getClassLoader());
   }

   private Collection<URL> getClasspathURLs(ClassLoader loader) {
      ArrayList<URL> urls = new ArrayList<>();

      if ("org.jboss.modules.ModuleClassLoader".equals(loader.getClass().getName())) {
         try {
            Field pathField = loader.getClass().getDeclaredField("paths");
            pathField.setAccessible(true);
            Object paths = pathField.get(loader);

            if (paths instanceof AtomicReference) {
               paths = ((AtomicReference) paths).get();
            }

            Method getAllPaths = paths.getClass().getDeclaredMethod("getAllPaths");
            getAllPaths.setAccessible(true);

            Map<String, Object> allPaths = (Map<String, Object>) getAllPaths.invoke(paths);
            for (Object l : allPaths.values()) {
               for (Object o : (List<?>) l) {
                  Field rootUrl = o.getClass().getDeclaredField("rootUrl");
                  rootUrl.setAccessible(true);
                  URL u = (URL) rootUrl.get(o);
                  String surl = u.toString().replace("vfs", "file");

                  urls.add(new URL(surl));
               }
            }

         } catch (NoSuchMethodException e) {
         } catch (SecurityException e) {
         } catch (IllegalAccessException e) {
         } catch (IllegalArgumentException e) {
         } catch (InvocationTargetException e) {
         } catch (NoSuchFieldException e) {
         } catch (MalformedURLException e) {
         }
      }

      if (loader instanceof URLClassLoader) {
         URLClassLoader ucl = (URLClassLoader) loader;
         urls.addAll(Arrays.asList(ucl.getURLs()));

      }

      if (null != loader.getParent())
         urls.addAll(getClasspathURLs(loader.getParent()));

      return urls;
   }

}
