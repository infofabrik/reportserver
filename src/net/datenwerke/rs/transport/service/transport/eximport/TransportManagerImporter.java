package net.datenwerke.rs.transport.service.transport.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class TransportManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "TransportManagerImporter";

   private final TransportTreeService transportTreeService;

   @Inject
   public TransportManagerImporter(
         TransportTreeService transportTreeService
         ) {
      this.transportTreeService = transportTreeService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { TransportManagerExporter.class };
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return transportTreeService;
   }
   
   @Override
   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {
      super.finishUpImportCreateMode(itemConfig, node);

      /* check key */
      if (node instanceof Transport) {
         String key = ((Transport) node).getKey();
         if (null != key) {
            Transport transportByKey = transportTreeService.getTransportByKey(key);
            if (null != transportByKey)
               throw new IllegalStateException("A transport with key '" + key + "' already exists");
         }
      }
   }

}