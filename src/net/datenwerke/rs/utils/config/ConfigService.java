package net.datenwerke.rs.utils.config;

import org.apache.commons.configuration2.Configuration;

public interface ConfigService {

	Configuration getConfig(String identifier);
	
	Configuration getConfig(String identifier, boolean useCache);

	Configuration newConfig();

	void storeConfig(Configuration config, String identifier);

	void clearCache();

	void clearCache(String identifier);

	Configuration getConfigFailsafe(String identifier);

	String getConfigAsJson(String identifier);

	String getConfigAsJsonFailsafe(String identifier);
}
