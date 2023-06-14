package net.datenwerke.rs.transport.service.transport.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class TransportManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "TransportManagerImporter";

   private final TransportTreeService transportTreeService;
   private final TransportService transportService;

   @Inject
   public TransportManagerImporter(
         TransportTreeService transportTreeService,
         TransportService transportService
         ) {
      this.transportTreeService = transportTreeService;
      this.transportService = transportService;
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

      /* recalculate key */
      if (node instanceof Transport)
         transportService.setInitialProperties((Transport) node, transportService.createInitialProperties());

   }

}