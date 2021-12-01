package net.datenwerke.rs.eximport.service.eximport.hooks;

import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ImportAllHook extends Hook {

	public void configure(ImportConfig config);
}
