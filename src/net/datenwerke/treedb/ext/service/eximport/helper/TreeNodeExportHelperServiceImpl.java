package net.datenwerke.treedb.ext.service.eximport.helper;

import java.util.function.Predicate;

import com.google.inject.Inject;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TreeNodeExportHelperServiceImpl implements TreeNodeExportHelperService {

   private final ExportService exportService;

   @Inject
   public TreeNodeExportHelperServiceImpl(ExportService exportService) {
      this.exportService = exportService;
   }

   @Override
   public String export(AbstractNode node, boolean addChildren, String name, boolean includePathToRoot,
         boolean flatten) {
      /* export report */
      ExportConfig exportConfig = createExportConfig(node, addChildren, name, includePathToRoot, flatten, n -> true);

      return exportService.exportIndent(exportConfig);
   }

   private void addChildren(ExportConfig exportConfig, AbstractNode node, boolean flatten,
         Predicate<AbstractNode> shouldInclude) {
      for (Object o : node.getChildren()) {
         AbstractNode childNode = (AbstractNode) o;
         if (flatten) {
            if (!childNode.isFolder() && shouldInclude.test(childNode))
               exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
         } else {
            if (shouldInclude.test(childNode))
               exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
         }
         addChildren(exportConfig, childNode, flatten, shouldInclude);
      }
   }
   
   @Override
   public ExportConfig createExportConfig(AbstractNode node, boolean addChildren, String name,
         boolean includePathToRoot, boolean flatten, Predicate<AbstractNode> shouldInclude) {
      if (includePathToRoot && flatten)
         throw new IllegalArgumentException("includePathToRoot and flatten can not be set at the same time");
      
      /* export report */
      ExportConfig exportConfig = new ExportConfig();
      exportConfig.setName(name);
      exportConfig.setNode(node);
      exportConfig.setIncludePathToRoot(includePathToRoot);
      if (flatten) {
         if (!node.isFolder() && shouldInclude.test(node))
            exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
      } else {
         if (shouldInclude.test(node))
            exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
      }

      if (addChildren)
         addChildren(exportConfig, node, flatten, shouldInclude);
      
      if (includePathToRoot) {
         AbstractNode<?> parent = node.getParent();
         while (null != parent) {
            // add parent to export config
            exportConfig.addItemConfig(new TreeNodeExportItemConfig(parent));
            parent = parent.getParent();
         }

      }
      
      return exportConfig;
   }

}
