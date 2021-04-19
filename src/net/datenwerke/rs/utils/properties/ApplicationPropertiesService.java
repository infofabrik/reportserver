package net.datenwerke.rs.utils.properties;

import java.util.List;

import org.apache.commons.configuration.Configuration;

public interface ApplicationPropertiesService {

	public final static String RS_PROPERTIES_FILE_NAME = "reportserver.properties";
	public final static String RS_PROPERTIES_OVERRIDE_FILE_NAME = "reportserver.overrides.properties";
	
	public Configuration getProperties();
	
	public String getString(String key);

	String getString(String key, String defaultValue);

	List<String> getList(String key);

	public Long getLong(String propertyExecuteUserId);

	public Integer getInteger(String key);
	public Integer getInteger(String key, int defaultValue);

	public boolean getBoolean(String key);
	public boolean getBoolean(String key, boolean defaultValue);

	public Long getLong(String key, Long defaultValue);
}
