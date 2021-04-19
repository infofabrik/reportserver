package net.datenwerke.rs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import net.datenwerke.gf.service.jpa.annotations.JpaUnit;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ReportServerPUStartup {

	public static final String PERSISTENCE_PROP_NAME = "persistence.properties";

	@Inject
	public ReportServerPUStartup(
			HookHandlerService hookHandlerService,
			ConfigDirService configDirService,
			@JpaUnit Properties jpaProperties
			) {

		loadPersistenceProperties(configDirService, jpaProperties);
		
		for(JpaPropertyConfiguratorHook hook : hookHandlerService.getHookers(JpaPropertyConfiguratorHook.class)){
			hook.configureProperties(jpaProperties);
		}
		
	}

	
	public static void loadPersistenceProperties(ConfigDirService configDirService, Properties jpaProperties){
		/* webapp config */
		try {
			PropertiesConfiguration peProps = new PropertiesConfiguration(PERSISTENCE_PROP_NAME);
			Properties props = new Properties();
			props.load(peProps.getURL().openStream());

			jpaProperties.putAll(props);
		} catch (ConfigurationException e) {
		} catch (IOException e) {
		}
		
		
		/* query config dir */
		if(configDirService.isEnabled()){
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
