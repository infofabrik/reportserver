package net.datenwerke.rs;

import java.util.Properties;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface JpaPropertyConfiguratorHook extends Hook {

   public void configureProperties(Properties props);
}
