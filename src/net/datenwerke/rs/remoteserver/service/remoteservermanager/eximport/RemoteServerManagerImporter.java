package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class RemoteServerManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "RemoteServerManagerImporter";

   private final RemoteServerTreeService remoteServerService;

   @Inject
   public RemoteServerManagerImporter(RemoteServerTreeService remoteServerService) {

      /* store objects */
      this.remoteServerService = remoteServerService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { RemoteServerManagerExporter.class };
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return remoteServerService;
   }
   
   @Override
   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {
      super.finishUpImportCreateMode(itemConfig, node);

      /* check key */
      if (node instanceof RemoteServerDefinition) {
         String key = ((RemoteServerDefinition) node).getKey();
         if (null != key) {
            RemoteServerDefinition remoteServerByKey = remoteServerService.getRemoteServerByKey(key);
            if (null != remoteServerByKey) {
               if (itemConfig instanceof ImportItemWithKeyConfig && ((ImportItemWithKeyConfig) itemConfig).isCleanKeys())
                  ((RemoteServerDefinition) node).setKey(null);
               else
                  throw new IllegalStateException("A remote server with key '" + key + "' already exists");
            }
         }
      }

   }

}