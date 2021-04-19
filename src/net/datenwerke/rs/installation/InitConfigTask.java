package net.datenwerke.rs.installation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class InitConfigTask implements DbInstallationTask {

	public static final String RSINIT_PROPERTIES = "rsinit.properties";
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private Provider<ConfigDirService> configDirServiceProvider;
	private Provider<UserManagerService> userManagerServiceProvider;
	private Provider<ConfigService> configServiceProvider;
	
	private HashMap<String, User> userRenameMap = new HashMap<>();
	

	@Inject
	public InitConfigTask(
			Provider<ConfigDirService> configDirServiceProvider, 
			Provider<UserManagerService> userManagerServiceProvider, 
			Provider<ConfigService> configServiceProvider
			){
				this.configDirServiceProvider = configDirServiceProvider;
				this.userManagerServiceProvider = userManagerServiceProvider;
				this.configServiceProvider = configServiceProvider;
	}
	
	@Override
	public void executeOnFirstRun() {
		ConfigDirService configDirService = configDirServiceProvider.get();
		if(configDirService.isEnabled()){
			File initpropsfile = new File(configDirService.getConfigDir(), RSINIT_PROPERTIES);
			if(initpropsfile.exists()){
				logger.info("Processing rsinit.properties");
				try {
					Properties props = new Properties();
					FileReader reader = new FileReader(initpropsfile);
					props.load(reader);
					
					for(Object o : props.keySet()){
						try{
							setProperty(String.valueOf(o), String.valueOf(props.get(o)));
						}catch(RuntimeException e){
							logger.warn("Failed to set property during initialization: " + String.valueOf(o));
						}
					}
					
					if(!Boolean.valueOf(props.getProperty("rsinit.keepfile", "false"))){
						IOUtils.closeQuietly(reader);
						FileUtils.forceDelete(initpropsfile);
					}
					userRenameMap.clear(); 
				} catch (FileNotFoundException e) {
					logger.warn("Failed to initialize using rsinit.properties", e);
				} catch (IOException e) {
					logger.warn("Failed to initialize using rsinit.properties", e);
				}
				
				
			}
		}
	}

	private void setProperty(String key, String val) {
		try{
			String[] parts = key.split("\\.");

			switch(parts[0]){
				case "usermanager":
					String username = parts[1];
					UserManagerService ums = userManagerServiceProvider.get();
					if(!userRenameMap.containsKey(username)){
						userRenameMap.put(username, ums.getUserByName(username));
					}
					User user = userRenameMap.get(username); 
					
					if("password".equals(parts[2])){
						ums.setPassword(user, val);
						ums.merge(user);
						break;
					}
					try {
						Method method = User.class.getMethod("set" + WordUtils.capitalize(parts[2]), String.class);
						method.invoke(user, val);
						ums.merge(user);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				break;
				
				case "cfg":
					String filename = key.replaceFirst("cfg\\.", "").replace(".", "/").replaceFirst("_cf.*", ".cf");
					String prop = key.replaceFirst(".*?_cf\\.", "");
					ConfigService configService = configServiceProvider.get();
					Configuration config = configService.getConfigFailsafe(filename);
					config.setProperty(prop, val);
					configService.storeConfig(config, filename);
				break;
				
				default:
					// ignore
				break;
			}
		}catch(ArrayIndexOutOfBoundsException oobe){
			logger.warn("Error processing rsinit.properties", oobe);
		}
	}

	@Override
	public void executeOnStartup() {
	}

}
