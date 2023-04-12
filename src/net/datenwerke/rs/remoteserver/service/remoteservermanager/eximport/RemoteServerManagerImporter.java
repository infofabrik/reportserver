package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
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

}