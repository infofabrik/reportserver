package net.datenwerke.rs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirServiceImpl;
import net.datenwerke.rs.configservice.service.configservice.LibDirClasspathHelper;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

public class LogSetupHelper {

   public static void InitRsLogging() {

      /* force hibernate logging to slf4j */
      System.setProperty("org.jboss.logging.provider", "slf4j");

      /* setup logback or jul */
      ConfigDirServiceImpl cdsi = new ConfigDirServiceImpl(null);
      URL logbackxml = findLogbackCfg(cdsi);
      URL loggingprops = findCfg(cdsi, "logging-rs.properties");

      Handler tomcatRsHandler = getTomcatRsHandler();
      boolean logbackHasConfig = hasConfig(logbackxml);

      LibDirClasspathHelper cphelper = new LibDirClasspathHelper(cdsi);
      ClassLoader[] classloaders = new ClassLoader[] { LoggerFactory.class.getClassLoader().getSystemClassLoader(),
            cphelper.getClassloader() };

      if (null != tomcatRsHandler) {
         try {
            cphelper.addUrl(cphelper.getClassloader(),
                  LogSetupHelper.class.getResource("/resources/optlib/slf4j-jdk14-1.7.32.jar"));

            LogManager lm = LogManager.getLogManager();

            if (null != loggingprops) {
               try (InputStream ins = loggingprops.openStream()) {
                  lm.readConfiguration(ins);
               }
            }

            if (null != tomcatRsHandler) {
               Logger rsRootLogger = lm.getLogger("");

               try {
                  String slevel = lm.getProperty(".level");
                  if (null != slevel) {
                     rsRootLogger.setLevel(Level.parse(slevel));
                  }
               } catch (Exception e) {
               }

               ArrayList<Handler> remove = new ArrayList<Handler>();
               remove.addAll(Arrays.asList(rsRootLogger.getHandlers()));
               for (Handler h : remove) {
                  rsRootLogger.removeHandler(h);
               }

               rsRootLogger.addHandler(tomcatRsHandler);
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (null != logbackxml && logbackHasConfig) {
         try {
            for (ClassLoader classloader : classloaders) {
               try {
                  /* only use classloaders that have slf4j (devmode hack) */
                  classloader.loadClass(LoggerFactory.class.getCanonicalName());

                  /*
                   * we get null if the filename contains the version maybe because of multiple
                   * dots e.g. logback-core-1.2.7.jar, so we remove the version number from
                   * filename. The version can be found in the MANIFEST.MF file.
                   */
                  cphelper.addUrl(classloader,
                        LogSetupHelper.class.getResource("/resources/optlib/jul-to-slf4j-1.7.32.jar"));
                  cphelper.addUrl(classloader,
                        LogSetupHelper.class.getResource("/resources/optlib/logback-classic.jar"));
                  cphelper.addUrl(classloader, LogSetupHelper.class.getResource("/resources/optlib/logback-core.jar"));
               } catch (Exception e) {
               }

            }

            for (ClassLoader classloader : classloaders) {
               try {
                  Class<LoggerFactory> lfc = (Class<LoggerFactory>) classloader
                        .loadClass(LoggerFactory.class.getCanonicalName());
                  Object context = lfc.getMethod("getILoggerFactory").invoke(lfc);

                  try {
                     Object configurator = classloader.loadClass("ch.qos.logback.classic.joran.JoranConfigurator")
                           .newInstance();
                     configurator.getClass()
                           .getMethod("setContext", classloader.loadClass("ch.qos.logback.core.Context"))
                           .invoke(configurator, context);
                     context.getClass().getMethod("reset").invoke(context);
                     configurator.getClass().getMethod("doConfigure", URL.class).invoke(configurator, logbackxml);
                  } catch (Exception e) {
                     if (!classloader.loadClass("ch.qos.logback.core.joran.spi.JoranException")
                           .isAssignableFrom(e.getClass()))
                        throw e;
                  }

                  Class<?> statusprinter = classloader.loadClass("ch.qos.logback.core.util.StatusPrinter");
                  statusprinter.getMethod("printInCaseOfErrorsOrWarnings",
                        classloader.loadClass("ch.qos.logback.core.Context")).invoke(statusprinter, context);
               } catch (Exception e) {
               }
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         try {
            for (ClassLoader classloader : classloaders) {
               try {
                  cphelper.addUrl(classloader,
                        LogSetupHelper.class.getResource("/resources/optlib/jul-to-slf4j-1.7.32.jar"));
               } catch (Exception e) {
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      /* setup jul to slf4j bridge if available */
      for (ClassLoader classloader : classloaders) {
         try {
            Class<?> bridgeHandler = classloader.loadClass("org.slf4j.bridge.SLF4JBridgeHandler");
            bridgeHandler.getMethod("removeHandlersForRootLogger").invoke(bridgeHandler);
            bridgeHandler.getMethod("install").invoke(bridgeHandler);
         } catch (Exception e) {
         }
      }
   }

   private static Handler getTomcatRsHandler() {
      try {
         LogManager lm = LogManager.getLogManager();
         Method loaderInfoMethod = lm.getClass().getDeclaredMethod("getClassLoaderInfo", java.lang.ClassLoader.class);
         loaderInfoMethod.setAccessible(true);
         Object[] args = new Object[1];
         args[0] = null;
         Object loaderInfo = loaderInfoMethod.invoke(lm, args);
         Field handlerField = loaderInfo.getClass().getDeclaredField("handlers");
         handlerField.setAccessible(true);

         Handler rsHandler = null;
         Map<String, Handler> handlerMap = (Map<String, Handler>) handlerField.get(loaderInfo);
         for (String key : handlerMap.keySet()) {
            if (key.matches("\\d*reportserver\\..*")) {
               rsHandler = handlerMap.get(key);
               break;
            }
         }

         return rsHandler;

      } catch (Exception e) {
      }

      return null;
   }

   private static URL findLogbackCfg(ConfigDirService cdsi) {
      URL logbackxml = null;
      if (null != System.getProperty("logback.configurationFile")) {
         try {
            logbackxml = new URL(System.getProperty("logback.configurationFile"));
         } catch (MalformedURLException e) {
            e.printStackTrace();
         }
      }
      if (null != System.getProperty("rs.logback.configurationFile")) {
         try {
            logbackxml = new URL(System.getProperty("logback.configurationFile"));
         } catch (MalformedURLException e) {
            e.printStackTrace();
         }
      }
      if (null == logbackxml) {
         logbackxml = findCfg(cdsi, "logback-rs.xml");
      }
      if (null == logbackxml) {
         logbackxml = findCfg(cdsi, "logback.xml");
      }

      return logbackxml;
   }

   private static boolean hasConfig(URL logbackxml) {
      try (InputStream is = logbackxml.openStream()) {
         String cfg = IOUtils.toString(is);
         if (null != cfg && !cfg.trim().isEmpty()) {
            return true;
         }
      } catch (IOException e) {
      }
      return false;
   }

   private static URL findCfg(ConfigDirService cdsi, String name) {
      URL cfg = null;
      if (null == cfg) {
         if (cdsi.isEnabled()) {
            File configDir = cdsi.getConfigDir();
            if (null != configDir) {
               File cand = new File(configDir, name);
               if (cand.exists()) {
                  try {
                     cfg = cand.toURI().toURL();
                  } catch (MalformedURLException e) {
                     e.printStackTrace();
                  }
               }
            }
         }
      }
      if (null == cfg) {
         cfg = LogSetupHelper.class.getResource("/" + name);
      }

      return cfg;
   }

}
