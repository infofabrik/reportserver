package net.datenwerke.gf.client.config;

import com.google.inject.ImplementedBy;

@ImplementedBy(ClientConfigJSONServiceImpl.class)
public interface ClientConfigJSONService {

	void setJSONConfig(String result);

	boolean getBoolean(String property, boolean defaultValue);

	String getString(String property, String defaultValue);

	
}
