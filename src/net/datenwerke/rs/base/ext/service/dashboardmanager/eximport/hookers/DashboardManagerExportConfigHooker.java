package net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.hookers;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.DashboardManagerExporter;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DashboardManagerExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public DashboardManagerExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractDashboardManagerNode))
         throw new IllegalArgumentException("node not an AbstractDashboardManagerNode");
      
      return exportHelper.createExportConfig(node, true, DashboardManagerExporter.EXPORTER_NAME);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractDashboardManagerNode;
   }

}
