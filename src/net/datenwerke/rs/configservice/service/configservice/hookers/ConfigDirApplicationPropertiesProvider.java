package net.datenwerke.rs.configservice.service.configservice.hookers;

import java.io.File;

import javax.inject.Inject;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesProviderHook;

public class ConfigDirApplicationPropertiesProvider implements ApplicationPropertiesProviderHook {

	private static final String REPORTSERVER_PROPERTIES = "reportserver.properties";
	
	private ConfigDirService configDirService;

	@Inject
	public ConfigDirApplicationPropertiesProvider(ConfigDirService configDirService) {
		this.configDirService = configDirService;
	}
	
	@Override
	public Configuration getConfig() throws ConfigurationException {
		if(!configDirService.isEnabled())
			return null;
		
		File cfg = new File(configDirService.getConfigDir(), REPORTSERVER_PROPERTIES);
		if(cfg.exists()){
		   FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
		         new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
		         .configure(new Parameters().properties()
		             .setFile(cfg));
			PropertiesConfiguration config = builder.getConfiguration();
			
			return config;
		} 
		
		return null;
	}
	

}
