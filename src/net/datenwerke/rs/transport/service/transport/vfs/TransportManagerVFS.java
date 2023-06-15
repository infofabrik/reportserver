package net.datenwerke.rs.transport.service.transport.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;

public class TransportManagerVFS extends TreeBasedVirtualFileSystem<AbstractTransportManagerNode> {

   private static final long serialVersionUID = 1757845347424881552L;
   private static final String FILESYSTEM_NAME = "transports";

   @Inject
   public TransportManagerVFS(Provider<TransportTreeService> remoteServerServiceProvider) {
      super(remoteServerServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected boolean isFolder(AbstractTransportManagerNode node) {
      return node instanceof TransportFolder;
   }

   @Override
   protected void doRename(AbstractTransportManagerNode node, String name) {
      if (node instanceof TransportFolder)
         ((TransportFolder) node).setName(name);
      else
         throw new IllegalArgumentException("Transports cannot be renamed");
   }

   @Override
   protected AbstractTransportManagerNode instantiateFolder(String folder) {
      return new TransportFolder(folder);
   }

   @Override
   protected String doGetNodeName(AbstractTransportManagerNode node) {
      if (node instanceof TransportFolder)
         return ((TransportFolder) node).getName();
      else
         return ((Transport) node).getName();
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractTransportManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof TransportFolder))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
