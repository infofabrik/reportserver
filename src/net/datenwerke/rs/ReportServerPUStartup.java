package net.datenwerke.rs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.configuration2.ConfigurationConverter;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.google.inject.Provider;

import net.datenwerke.gf.service.jpa.annotations.JpaUnit;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;

public class ReportServerPUStartup {

   public static final String PERSISTENCE_PROP_NAME = "persistence.properties";

   @Inject
   public ReportServerPUStartup(
         HookHandlerService hookHandlerService, 
         ConfigDirService configDirService,
         final @JpaUnit Properties jpaProperties,
         Provider<HibernateCategoryProviderHooker> generalInfoHibernateCategoryProviderHooker
         ) {

      loadPersistenceProperties(configDirService, jpaProperties);

      hookHandlerService.getHookers(JpaPropertyConfiguratorHook.class)
         .forEach(hook -> hook.configureProperties(jpaProperties));

      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoHibernateCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 20);
   }

   public static void loadPersistenceProperties(ConfigDirService configDirService, Properties jpaProperties) {
      /* webapp config */
      try {
         FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
               PropertiesConfiguration.class)
                     .configure(new Parameters().properties().setFileName(PERSISTENCE_PROP_NAME));

         PropertiesConfiguration peProps = builder.getConfiguration();
         Properties props = ConfigurationConverter.getProperties(peProps);

         jpaProperties.putAll(props);
      } catch (ConfigurationException e) {
      }

      /* query config dir */
      if (configDirService.isEnabled()) {
         try {
            File configDir = configDirService.getConfigDir();
            Properties props = new Properties();
            props.load(new FileInputStream(new File(configDir, PERSISTENCE_PROP_NAME)));

            jpaProperties.putAll(props);
         } catch (FileNotFoundException e) {
         } catch (IOException e) {
         }
      }
   }
}
