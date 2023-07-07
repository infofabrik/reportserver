package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.hookers;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class RemoteServerExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public RemoteServerExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractRemoteServerManagerNode))
         throw new IllegalArgumentException("node not an AbstractRemoteServerManagerNode");
      
      return exportHelper.createExportConfig(node, true, RemoteServerManagerExporter.EXPORTER_NAME, false);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractRemoteServerManagerNode;
   }

}
