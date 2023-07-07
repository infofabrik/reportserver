package net.datenwerke.rs.transport.service.transport.eximport.hookers;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TransportExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public TransportExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractTransportManagerNode))
         throw new IllegalArgumentException("node not an AbstractTransportManagerNode");
      
      return exportHelper.createExportConfig(node, true, TransportManagerExporter.EXPORTER_NAME, false);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractTransportManagerNode;
   }

}
