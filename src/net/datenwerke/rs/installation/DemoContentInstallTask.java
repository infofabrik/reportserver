package net.datenwerke.rs.installation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.pkg.service.pkg.PackagedScriptHelperService;

public class DemoContentInstallTask implements DbInstallationTask {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private Provider<PackagedScriptHelperService> packagedScriptHelper;
   private FileServerService fileServerService;
   private Provider<ConfigDirService> configDirServiceProvider;

   @Inject
   public DemoContentInstallTask(
         Provider<PackagedScriptHelperService> packagedScriptHelper,
         FileServerService fileServerService, 
         Provider<ConfigDirService> configDirServiceProvider
         ) {
      this.packagedScriptHelper = packagedScriptHelper;
      this.fileServerService = fileServerService;
      this.configDirServiceProvider = configDirServiceProvider;
   }

   @Override
   public void executeOnFirstRun() {
      ConfigDirService configDirService = configDirServiceProvider.get();
      if (configDirService.isEnabled()) {
         File initpropsfile = new File(configDirService.getConfigDir(), InitConfigTask.RSINIT_PROPERTIES);
         if (initpropsfile.exists()) {
            try {
               Properties props = new Properties();
               FileReader reader = new FileReader(initpropsfile);
               props.load(reader);
               IOUtils.closeQuietly(reader);

               if (Boolean.valueOf(props.getProperty("democontent.install", "false"))) {
                  PackagedScriptHelperService helper = packagedScriptHelper.get();
                  Optional<File> demobuilderFile = helper.listPackages()
                        .stream()
                        .filter(f -> f.getName().startsWith("demobuilder-"))
                        .findAny();
                  if (! demobuilderFile.isPresent())
                     return;

                  if (helper.validateZip(demobuilderFile.get(), false)) {

                     logger.info("Executing package: " + demobuilderFile.get().getAbsolutePath());
                     FileServerFolder targetDir = null;
                     try {
                        targetDir = helper.extractPackageTemporarily(new FileInputStream(demobuilderFile.get()));
                        helper.executePackage(targetDir);
                     } catch (FileNotFoundException e) {
                        e.printStackTrace();
                     } catch (IOException e) {
                        e.printStackTrace();
                     } finally {
                        if (null != targetDir)
                           fileServerService.forceRemove(targetDir);
                     }
                  }
               }
            } catch (IOException e) {
               logger.warn("Failed to initialize using rsinit.properties", e);
            }

         }
      }
   }

   @Override
   public void executeOnStartup() {
      // TODO Auto-generated method stub

   }

}
