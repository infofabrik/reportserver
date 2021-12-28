package net.datenwerke.rs.saiku.service.hooks;

import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

@HookConfig
public interface SaikuQueryParameterAdapterHook extends Hook {

   public void adaptParameters(Map<String, String> parameters, SaikuReport report);

}
