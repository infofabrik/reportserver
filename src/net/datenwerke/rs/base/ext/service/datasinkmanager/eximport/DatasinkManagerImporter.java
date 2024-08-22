package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class DatasinkManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "DatasinkManagerImporter";

   private final DatasinkTreeService datasinkService;
   
   private final KeyNameGeneratorService keyNameGeneratorService;

   @Inject
   public DatasinkManagerImporter(DatasinkTreeService datasinkService, KeyNameGeneratorService keyNameGeneratorService) {

      /* store objects */
      this.datasinkService = datasinkService;
      this.keyNameGeneratorService = keyNameGeneratorService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { DatasinkManagerExporter.class };
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return datasinkService;
   }
   
   @Override
   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {
      super.finishUpImportCreateMode(itemConfig, node);

      /* check key */
      if (node instanceof DatasinkDefinition) {
         String key = ((DatasinkDefinition) node).getKey();
         if (null != key) {
            DatasinkDefinition datasinkByKey = datasinkService.getDatasinkByKey(key);
            if (null != datasinkByKey) {
               if (itemConfig instanceof ImportItemWithKeyConfig && ((ImportItemWithKeyConfig) itemConfig).isCreateRandomKeys())
                  ((DatasinkDefinition) node).setKey(keyNameGeneratorService.generateDefaultKey());
               else
                  throw new IllegalStateException("A datasink with key '" + key + "' already exists");
            }
         }
      }

   }

}