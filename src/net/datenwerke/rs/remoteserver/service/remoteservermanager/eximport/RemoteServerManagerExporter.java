package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class RemoteServerManagerExporter extends TreeNodeExporter {

   private static final String EXPORTER_ID = "RemoteServerManagerExporter";
   
   public static final String EXPORTER_NAME = "RemoteServer-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractRemoteServerManagerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { RemoteServerFolder.class, RemoteServerDefinition.class };
   }

}