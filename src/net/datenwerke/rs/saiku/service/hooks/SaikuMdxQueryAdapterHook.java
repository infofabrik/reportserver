package net.datenwerke.rs.saiku.service.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

@HookConfig
public interface SaikuMdxQueryAdapterHook extends Hook {

   public String adaptMdx(String mdx, SaikuReport report);

}
