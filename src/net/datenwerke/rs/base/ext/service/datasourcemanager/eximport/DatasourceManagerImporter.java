package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class DatasourceManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "DatasourceManagerImporter";

   private final DatasourceService datasourceService;
   
   private final KeyNameGeneratorService keyNameGeneratorService;

   @Inject
   public DatasourceManagerImporter(DatasourceService datasourceService, KeyNameGeneratorService keyNameGeneratorService) {

      /* store objects */
      this.datasourceService = datasourceService;
      this.keyNameGeneratorService = keyNameGeneratorService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { DatasourceManagerExporter.class };
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return datasourceService;
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }
   
   @Override
   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {
      super.finishUpImportCreateMode(itemConfig, node);

      /* check key */
      if (node instanceof DatasourceDefinition) {
         String key = ((DatasourceDefinition) node).getKey();
         if (null != key) {
            DatasourceDefinition datasourceByKey = datasourceService.getDatasourceByKey(key);
            if (null != datasourceByKey) {
               if (itemConfig instanceof ImportItemWithKeyConfig && ((ImportItemWithKeyConfig) itemConfig).isCleanKeys())
                  ((DatasourceDefinition) node).setKey(keyNameGeneratorService.generateDefaultKey());
               else
                  throw new IllegalStateException("A datasource with key '" + key + "' already exists");
            }
         }
      }
   }
}
