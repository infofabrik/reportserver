package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;

import javax.inject.Inject;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;

public class ConfigDirConfigStore extends AbstractConfigStore {
	
	private final static String CONFIG_FOLDER = "config";

	private ConfigDirService configDirService;

	@Inject
	public ConfigDirConfigStore(ConfigDirService configDirService) {
		this.configDirService = configDirService;
	}
	
	@Override
	public HierarchicalConfiguration loadConfig(String identifier) throws ConfigurationException {
		if(!configDirService.isEnabled())
			return null;
		
		File cfg = new File(configDirService.getConfigDir(), CONFIG_FOLDER + "/" + identifier);
		if(cfg.exists()){
		    BasicConfigurationBuilder<XMLConfiguration> builder = createBaseConfig();
		    XMLConfiguration config = builder.getConfiguration();
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.load(cfg);
			return config;
		}
		
		return null;
	}

}
