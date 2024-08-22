package net.datenwerke.gf.service.config;

import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ClientConfigExposerHook extends Hook {

   public Map<String, String> exposeConfig(String identifier);
}
