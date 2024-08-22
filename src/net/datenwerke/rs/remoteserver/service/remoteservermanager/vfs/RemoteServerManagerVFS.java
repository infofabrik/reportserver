package net.datenwerke.rs.remoteserver.service.remoteservermanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;

public class RemoteServerManagerVFS extends TreeBasedVirtualFileSystem<AbstractRemoteServerManagerNode> {

   /**
    * 
    */
   private static final long serialVersionUID = 1757845347424881552L;
   private static final String FILESYSTEM_NAME = "remoteservers";

   @Inject
   public RemoteServerManagerVFS(Provider<RemoteServerTreeService> remoteServerServiceProvider) {
      super(remoteServerServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected boolean isFolder(AbstractRemoteServerManagerNode node) {
      return node instanceof RemoteServerFolder;
   }

   @Override
   protected void doRename(AbstractRemoteServerManagerNode node, String name) {
      if (node instanceof RemoteServerFolder)
         ((RemoteServerFolder) node).setName(name);
      else
         ((RemoteServerDefinition) node).setName(name);

   }

   @Override
   protected AbstractRemoteServerManagerNode instantiateFolder(String folder) {
      return new RemoteServerFolder(folder);
   }

   @Override
   protected String doGetNodeName(AbstractRemoteServerManagerNode node) {
      if (node instanceof RemoteServerFolder)
         return ((RemoteServerFolder) node).getName();
      else
         return ((RemoteServerDefinition) node).getName();
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractRemoteServerManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof RemoteServerFolder))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
