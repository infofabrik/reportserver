package net.datenwerke.rs.base.ext.service.reportmanager.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.reportmanager.ReportExportOptions;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ReportExportConfigHooker implements ExportConfigHook {

   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      /* export report */
      ExportConfig exportConfig = new ExportConfig();
      exportConfig.setName("Report-Export");
      exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
      
      if (!(options instanceof ReportExportOptions))
         throw new IllegalArgumentException("options not of type ReportExportOptions");
      if (!(node instanceof AbstractReportManagerNode))
         throw new IllegalArgumentException("node not an AbstractReportManagerNode");
      
      ReportExportOptions reportExportOptions = (ReportExportOptions) options;
      addChildren(exportConfig, (AbstractReportManagerNode)node, reportExportOptions.includeVariants());
      
      return exportConfig;
   }
   
   private void addChildren(ExportConfig exportConfig, AbstractReportManagerNode report, boolean includeVariants) {
      for (AbstractReportManagerNode childNode : report.getChildren()) {
         if (includeVariants || !(childNode instanceof ReportVariant)) {
            exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
            addChildren(exportConfig, childNode, includeVariants);
         }
      }
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractReportManagerNode;
   }

}
