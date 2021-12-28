package net.datenwerke.rs.eximport.service.eximport.hooks;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ExportAllHook extends Hook {

   public void configure(ExportConfig config);
}
