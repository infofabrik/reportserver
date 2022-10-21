package net.datenwerke.rs.eximport.service.eximport.im.http.hooks;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;

public interface HttpImportConfigurationProviderHook extends Hook {

   boolean consumes(String id);

   void configureImport(ImportConfigDto config) throws IllegalImportConfigException;

   void validate(ImportConfigDto config) throws IllegalImportConfigException;

}
