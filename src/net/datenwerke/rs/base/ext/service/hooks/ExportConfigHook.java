package net.datenwerke.rs.base.ext.service.hooks;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface ExportConfigHook extends Hook {

   boolean consumes(AbstractNode node);

   ExportConfig configure(AbstractNode node, ExportOptions options);
}
