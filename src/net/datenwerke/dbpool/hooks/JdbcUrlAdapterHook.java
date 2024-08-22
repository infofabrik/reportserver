package net.datenwerke.dbpool.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface JdbcUrlAdapterHook extends Hook {

   public String adaptJdbcUrl(String url);
}
