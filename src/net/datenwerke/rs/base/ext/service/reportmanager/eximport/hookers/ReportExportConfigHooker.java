package net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.reportmanager.ReportExportOptions;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ReportExportConfigHooker implements ExportConfigHook {

   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(options instanceof ReportExportOptions))
         throw new IllegalArgumentException("options not of type ReportExportOptions");
      if (!(node instanceof AbstractReportManagerNode))
         throw new IllegalArgumentException("node not an AbstractReportManagerNode");
      
      /* export report */
      ExportConfig exportConfig = new ExportConfig();
      exportConfig.setName(ReportManagerExporter.EXPORTER_NAME);
      
      if (options.flatten()) {
         if (!node.isFolder()) {
            exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
            exportConfig.setNode(node);
         }
      } else {
         exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
         exportConfig.setNode(node);
      }
      
      ReportExportOptions reportExportOptions = (ReportExportOptions) options;
      addChildren(exportConfig, (AbstractReportManagerNode) node, reportExportOptions.includeVariants(),
            options.flatten());
      
      return exportConfig;
   }
   
   private void addChildren(ExportConfig exportConfig, AbstractReportManagerNode report, boolean includeVariants, boolean flatten) {
      for (AbstractReportManagerNode childNode : report.getChildren()) {
         if (childNode instanceof ReportVariant) {
            if (includeVariants) {
               exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
            }
         } else {
            if (flatten) {
               if (!childNode.isFolder())
                  exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
            } else {
               exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
            }
            addChildren(exportConfig, childNode, includeVariants, flatten);
         }
      }
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractReportManagerNode;
   }

}
