package net.datenwerke.rs.utils.properties;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;

public interface ApplicationPropertiesProviderHook extends Hook {

	public Configuration getConfig() throws ConfigurationException;
	
}
