package net.datenwerke.rs.eximport.service.eximport.im.http.hooks;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;

public interface HttpImportPostProcessProviderHook extends Hook {

   boolean consumes(String id);

   void postProcess(ImportPostProcessConfigDto config, ImportResult result);

}
