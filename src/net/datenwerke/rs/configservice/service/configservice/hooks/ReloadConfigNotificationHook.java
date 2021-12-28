package net.datenwerke.rs.configservice.service.configservice.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig(generateAdapter = true)
public interface ReloadConfigNotificationHook extends Hook {

   public void reloadConfig();

   public void reloadConfig(String identifier);

}
