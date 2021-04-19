package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExImportOptions;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporterConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ExportAllDatasinksHooker implements ExportAllHook{
   
   private final DatasinkService dsService;
   
   @Inject
   public ExportAllDatasinksHooker(DatasinkService dsService) {
      this.dsService = dsService;
   }

   @Override
   public void configure(ExportConfig config) {
      TreeNodeExporterConfig specConfig = new TreeNodeExporterConfig();
      specConfig.addExImporterOptions(TreeNodeExImportOptions.INCLUDE_OWNER, TreeNodeExImportOptions.INCLUDE_SECURITY);
      config.addSpecificExporterConfigs(specConfig);
      
      for(AbstractNode<?> node: dsService.getAllNodes())
         config.addItemConfig(new TreeNodeExportItemConfig(node));
      
   }

}