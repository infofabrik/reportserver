package net.datenwerke.treedb.ext.service.eximport.helper;

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
   public String export(AbstractNode node, boolean addChildren, String name, boolean includePathToRoot) {
      /* export report */
      ExportConfig exportConfig = createExportConfig(node, addChildren, name, includePathToRoot);

      return exportService.exportIndent(exportConfig);
   }

   private void addChildren(ExportConfig exportConfig, AbstractNode node) {
      for (Object o : node.getChildren()) {
         AbstractNode childNode = (AbstractNode) o;
         exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
         addChildren(exportConfig, childNode);
      }
   }
   
   @Override
   public ExportConfig createExportConfig(AbstractNode node, boolean addChildren, String name, boolean includePathToRoot) {
      /* export report */
      ExportConfig exportConfig = new ExportConfig();
      exportConfig.setName(name);
      exportConfig.setNode(node);
      exportConfig.setIncludePathToRoot(includePathToRoot);
      exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));

      if (addChildren)
         addChildren(exportConfig, node);
      
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
