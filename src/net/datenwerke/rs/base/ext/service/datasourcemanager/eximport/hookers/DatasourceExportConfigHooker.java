package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DatasourceExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public DatasourceExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractDatasourceManagerNode))
         throw new IllegalArgumentException("node not an AbstractDatasourceManagerNode");
      
      return exportHelper.createExportConfig(node, true, DatasourceManagerExporter.EXPORTER_NAME, false);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractDatasourceManagerNode;
   }

}
