package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerExporter;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DatasinkExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public DatasinkExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractDatasinkManagerNode))
         throw new IllegalArgumentException("node not an AbstractDatasinkManagerNode");
      
      return exportHelper.createExportConfig(node, true, DatasinkManagerExporter.EXPORTER_NAME, false,
            options.flatten(), n -> true);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractDatasinkManagerNode;
   }

}
