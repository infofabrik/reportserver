package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration;

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
			HierarchicalConfiguration config = createBaseConfig();
			try {
				((FileConfiguration)config).load(new FileInputStream(cfg));
			} catch (FileNotFoundException e) {
				return null;
			}
			return config;
		}
		
		return null;
	}

}
