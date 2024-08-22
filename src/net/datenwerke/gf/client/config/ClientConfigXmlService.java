package net.datenwerke.gf.client.config;

import com.google.inject.ImplementedBy;

@ImplementedBy(ClientConfigXmlServiceImpl.class)
public interface ClientConfigXmlService {

   void setXmlConfig(String xml);

   boolean getBoolean(String property, boolean defaultValue);

   String getString(String property, String defaultValue);

}
