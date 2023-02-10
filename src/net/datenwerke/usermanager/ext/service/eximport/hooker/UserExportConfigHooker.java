package net.datenwerke.usermanager.ext.service.eximport.hooker;

import javax.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.usermanager.ext.service.eximport.UserManagerExporter;

public class UserExportConfigHooker implements ExportConfigHook {

   private final TreeNodeExportHelperService exportHelper; 
   
   @Inject
   public UserExportConfigHooker(
         TreeNodeExportHelperService exportHelper
         ) {
      this.exportHelper = exportHelper;
   }
   
   @Override
   public ExportConfig configure(AbstractNode node, ExportOptions options) {
      if (!(node instanceof AbstractUserManagerNode))
         throw new IllegalArgumentException("node not an AbstractUserManagerNode");
      
      return exportHelper.createExportConfig(node, true, UserManagerExporter.EXPORTER_NAME);
   }

   @Override
   public boolean consumes(AbstractNode node) {
      return node instanceof AbstractUserManagerNode;
   }

}
