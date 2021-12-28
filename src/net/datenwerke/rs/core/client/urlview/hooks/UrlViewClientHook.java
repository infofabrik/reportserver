package net.datenwerke.rs.core.client.urlview.hooks;

import java.util.List;
import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface UrlViewClientHook extends Hook {

   public void processUrlViewConfig(Map<String, Map<String, List<String[]>>> config);

}
