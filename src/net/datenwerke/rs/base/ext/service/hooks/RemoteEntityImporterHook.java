package net.datenwerke.rs.base.ext.service.hooks;

import java.util.Map;

import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.ext.service.RemoteEntityImports;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface RemoteEntityImporterHook extends Hook {

   boolean consumes(RemoteEntityImports importType);

   ImportResult importRemoteEntity(ImportConfig config, AbstractNode localTarget);

   Map<String, String> checkImportRemoteEntity(ImportConfig config, AbstractNode localTarget,
         Map<String, String> previousCheckResults);
}
