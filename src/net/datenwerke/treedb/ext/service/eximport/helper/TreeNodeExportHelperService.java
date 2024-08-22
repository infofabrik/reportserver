package net.datenwerke.treedb.ext.service.eximport.helper;

import java.util.function.Predicate;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface TreeNodeExportHelperService {

   String export(AbstractNode node, boolean addChildren, String name, boolean includePathToRoot, boolean flatten);

   ExportConfig createExportConfig(AbstractNode node, boolean addChildren, String name, boolean includePathToRoot,
         boolean flatten, Predicate<AbstractNode> shouldInclude);
}
