package net.datenwerke.rs.utils.properties;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

public interface ApplicationPropertiesProviderHook extends Hook {

	public Configuration getConfig() throws ConfigurationException;
	
}
