package net.datenwerke.rs.base.ext.service.datasinkmanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;

public class DatasinkManagerVFS extends TreeBasedVirtualFileSystem<AbstractDatasinkManagerNode> {

   /**
    * 
    */
   private static final long serialVersionUID = 1757845347424881552L;
   private static final String FILESYSTEM_NAME = "datasinks";

   @Inject
   public DatasinkManagerVFS(
         Provider<DatasinkTreeService> datasinkServiceProvider
         ) {
      super(datasinkServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected boolean isFolder(AbstractDatasinkManagerNode node) {
      return node instanceof DatasinkFolder;
   }

   @Override
   protected void doRename(AbstractDatasinkManagerNode node, String name) {
      if (node instanceof DatasinkFolder)
         ((DatasinkFolder) node).setName(name);
      else
         ((DatasinkDefinition) node).setName(name);

   }

   @Override
   protected AbstractDatasinkManagerNode instantiateFolder(String folder) {
      return new DatasinkFolder(folder);
   }

   @Override
   protected String doGetNodeName(AbstractDatasinkManagerNode node) {
      if (node instanceof DatasinkFolder)
         return ((DatasinkFolder) node).getName();
      else
         return ((DatasinkDefinition) node).getName();
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractDatasinkManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof DatasinkFolder))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
