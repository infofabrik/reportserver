package net.datenwerke.rs.saiku.service.saiku.reportengine.hooks;

import java.util.Properties;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface OlapConnectionPropertiesHook extends Hook {

   String adaptUrl(String url);

   void adaptProperties(Properties props);

   String adaptDriver(String driver);

}