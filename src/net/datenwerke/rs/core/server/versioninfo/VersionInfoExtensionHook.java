package net.datenwerke.rs.core.server.versioninfo;

import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface VersionInfoExtensionHook extends Hook {

   public String getKey();

   public Object getValue(Map parameterMap);

}
