package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class ConfigDirServiceImpl implements ConfigDirService {
	
	public final static String SYSTEM_PROPERTY_NAME = "rs.configdir";
	
	private String configPath;
	private ServletContext servletContext;
	
	
	@Inject
	public ConfigDirServiceImpl(ServletContext servletContext) {
		this.servletContext = servletContext;
		
		this.configPath = getConfigPath();
	}
	
	private String getConfigPath(){
		String ctxpath = null;
		if(null != servletContext)
			ctxpath = servletContext.getContextPath().replaceAll("\\W", ".");
		String prop = System.getProperty(SYSTEM_PROPERTY_NAME + "." + ctxpath);
		
		if(null == prop){
			prop = System.getProperty(SYSTEM_PROPERTY_NAME);
		}
		
		return prop;
	}
	
	@Override
	public File getConfigDir(){
		if(!isEnabled())
			return null;
		
		return new File(configPath);
	}

	@Override
	public boolean isEnabled() {
		return null != configPath;
	}

}
