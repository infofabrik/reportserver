package net.datenwerke.rs.teamspace.service.teamspace.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.im.entity.EntityImportItemConfig;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.teamspace.service.teamspace.eximport.TeamSpaceExporter;

public class ImportAllTeamspacesHooker implements ImportAllHook {

   private final ExportDataAnalyzerService analizerService;

   @Inject
   public ImportAllTeamspacesHooker(
         ExportDataAnalyzerService analizerService
         ) {
      this.analizerService = analizerService;
   }

   @Override
   public void configure(ImportConfig config) {
      try {
         for (ExportedItem item : analizerService.getExportedItemsFor(config.getExportDataProvider(),
               TeamSpaceExporter.class)) {
            EntityImportItemConfig itemConfig = new EntityImportItemConfig(item.getId());
            config.addItemConfig(itemConfig);
         }
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException(e);
      }
   }

}
