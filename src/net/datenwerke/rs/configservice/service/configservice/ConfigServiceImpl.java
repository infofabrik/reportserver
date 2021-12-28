package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Provider;

import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ConfigStoreHook;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.installation.PackagedScriptHelper;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.config.ConfigFileNotFoundException;

@Singleton
public class ConfigServiceImpl implements ConfigService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Map<String, Configuration> cache = new ConcurrentHashMap<String, Configuration>();

   private final FileServerService fileService;
   private final TerminalService terminalService;
   private final HookHandlerService hookHandler;
   private final Provider<PackagedScriptHelper> packagedScriptHelperProvider;

   @Inject
   public ConfigServiceImpl(FileServerService fileService, TerminalService terminalService,
         HookHandlerService hookHandler, Provider<PackagedScriptHelper> packagedScriptHelperProvider) {

      /* store objects */
      this.fileService = fileService;
      this.terminalService = terminalService;
      this.hookHandler = hookHandler;
      this.packagedScriptHelperProvider = packagedScriptHelperProvider;
   }

   public Provider<Configuration> getConfigProvider(final String identifier) {
      return new Provider<Configuration>() {
         @Override
         public Configuration get() {
            return getConfig(identifier);
         }
      };
   }

   @Override
   public Configuration getConfigFailsafe(String identifier) {
      try {
         Configuration config = getConfig(identifier);

         if (null == config)
            return new BaseConfiguration();

         return config;
      } catch (Exception e) {
         logger.warn("Configfile " + identifier + " could not be loaded. Default values are in effect.");
         return new BaseConfiguration();
      }
   }

   @Override
   public Configuration getConfig(String identifier) {
      return getConfig(identifier, true);
   }

   @Override
   public String getConfigAsXml(String identifier) {
      try {
         Object object = terminalService.getObjectByLocation("/fileserver/etc/" + identifier, false);
         if (null == object || !(object instanceof FileServerFile))
            throw new ConfigFileNotFoundException("Could not find config for " + identifier);

         FileServerFile file = (FileServerFile) object;

         byte[] data = file.getData();
         if (null == data)
            throw new ConfigFileNotFoundException("config file is empty " + identifier);

         return new String(data);
      } catch (ObjectResolverException e) {
         throw new ConfigFileNotFoundException("Could not find config for " + identifier, e);
      }
   }

   @Override
   public String getConfigAsXmlFailsafe(String identifier) {
      try {
         Object object = terminalService.getObjectByLocation("/fileserver/etc/" + identifier, false);
         if (null == object || !(object instanceof FileServerFile))
            throw new ConfigFileNotFoundException("Could not find config for " + identifier);

         FileServerFile file = (FileServerFile) object;

         byte[] data = file.getData();
         if (null == data)
            throw new ConfigFileNotFoundException("config file is empty " + identifier);

         return new String(data);
      } catch (Exception e) {
         logger.warn("Configfile " + identifier + " could not be loaded. Default values are in effect.");
         return "";
      }
   }

   @Override
   public Configuration getConfig(String identifier, boolean useCache) {
      try {
         if (useCache && cache.containsKey(identifier))
            return cache.get(identifier);

         /* try to load config from configstores */
         HierarchicalConfiguration config = null;
         for (ConfigStoreHook confstore : hookHandler.getHookers(ConfigStoreHook.class)) {
            config = confstore.getObject().loadConfig(identifier);
            if (null != config)
               break;
         }
         if (null == config)
            throw new ConfigFileNotFoundException("Could not find config for " + identifier);

         cache.put(identifier, config);

         return config;
      } catch (ConfigurationException e) {
         throw new IllegalArgumentException("Failed processing config from " + identifier + ": " + e.getMessage(), e);
      }
   }

   @Override
   public Configuration newConfig() {
      XMLConfiguration config = new XMLConfiguration();

      return config;
   }

   @Override
   public void storeConfig(Configuration config, final String identifier) {
      if (!(config instanceof XMLConfiguration))
         throw new IllegalArgumentException();
      try {
         Object object = null;
         try {
            object = terminalService.getObjectByLocation("/fileserver/etc/" + identifier, false);
         } catch (ObjectResolverException e) {
         }
         if (null == object) {
            object = fileService.createFileAtLocation("/fileserver/etc/" + identifier, false);
         } else if (null != object && !(object instanceof FileServerFile))
            throw new IllegalArgumentException("Could not find config for " + identifier);

         FileServerFile file = (FileServerFile) object;
         file.setContentType("application/xml");

         ByteArrayOutputStream out = new ByteArrayOutputStream();
         FileHandler handler = new FileHandler((XMLConfiguration) config);
         handler.save(out);
         file.setData(out.toByteArray());
         fileService.merge(file);

         /* remove from cache */
         if (cache.containsKey(identifier))
            cache.remove(identifier);

         hookHandler.getHookers(ReloadConfigNotificationHook.class).forEach(hooker -> hooker.reloadConfig(identifier));
      } catch (ConfigurationException e) {
         throw new IllegalArgumentException("Could not find config for " + identifier, e);
      }
   }

   @Override
   public void clearCache(final String identifier) {
      cache.remove(identifier);

      hookHandler.getHookers(ReloadConfigNotificationHook.class).forEach(hooker -> hooker.reloadConfig(identifier));
   }

   @Override
   public void clearCache() {
      cache.clear();
      hookHandler.getHookers(ReloadConfigNotificationHook.class).forEach(ReloadConfigNotificationHook::reloadConfig);
   }

   @Override
   public FileServerFolder extractBasicConfigFilesTo(String folderName) throws FileNotFoundException, IOException {
      FileServerFolder zipDir = null;
      String pathToFolder = "/" + folderName;
      FileServerFolder targetDir = (FileServerFolder) fileService.getNodeByPath(pathToFolder, false);
      /*
       * config files are extracted temporarily to /tmp. We check if this folder
       * exists and if does not exist previously, we delete it
       */
      boolean tmpFolderDidExist = (null == fileService.getNodeByPath("/tmp", false)) ? false : true;
      PackagedScriptHelper helper = packagedScriptHelperProvider.get();
      File pkgDir = helper.getPackageDirectory();

      if (null == targetDir) {
         targetDir = new FileServerFolder(folderName);
         AbstractFileServerNode root = fileService.getRoots().get(0);
         root.addChild(targetDir);
         fileService.persist(targetDir);
         fileService.merge(root);
      }

      if (pkgDir.exists() && pkgDir.isDirectory()) {
         Optional<File> baseConfigPackageScriptFile = helper.listPackages().stream()
               .filter(f -> f.getName().matches("^baseconfig-RS.*[.]zip$")).findAny();

         if (baseConfigPackageScriptFile.isPresent() && helper.validateZip(baseConfigPackageScriptFile.get(), true)) {
            try {
               zipDir = helper.extractPackageTemporarily(new FileInputStream(baseConfigPackageScriptFile.get()));
               helper.executePackage(zipDir, "", false, true, Optional.of(pathToFolder));
            } finally {
               if (null != zipDir)
                  fileService.forceRemove(zipDir);
               if (!tmpFolderDidExist)
                  fileService.forceRemove(helper.getFileServerTempDir());
            }
         }
      }
      return targetDir;
   }
}
