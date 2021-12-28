package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExImportOptions;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporterConfig;

public class ExportAllDatasinksHooker implements ExportAllHook {

   private final DatasinkTreeService dsService;

   @Inject
   public ExportAllDatasinksHooker(DatasinkTreeService dsService) {
      this.dsService = dsService;
   }

   @Override
   public void configure(final ExportConfig config) {
      TreeNodeExporterConfig specConfig = new TreeNodeExporterConfig();
      specConfig.addExImporterOptions(TreeNodeExImportOptions.INCLUDE_OWNER, TreeNodeExImportOptions.INCLUDE_SECURITY);
      config.addSpecificExporterConfigs(specConfig);

      dsService.getAllNodes().forEach(node -> config.addItemConfig(new TreeNodeExportItemConfig(node)));

   }

}