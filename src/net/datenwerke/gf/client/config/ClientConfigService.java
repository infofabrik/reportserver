package net.datenwerke.gf.client.config;

import com.google.inject.ImplementedBy;

@ImplementedBy(ClientConfigServiceImpl.class)
public interface ClientConfigService {

	void setMainConfig(String result);

	boolean getBoolean(String property, boolean defaultValue);

	String getString(String property, String defaultValue);

	
}
