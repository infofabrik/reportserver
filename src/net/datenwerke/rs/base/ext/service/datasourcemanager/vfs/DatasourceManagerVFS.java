package net.datenwerke.rs.base.ext.service.datasourcemanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;

public class DatasourceManagerVFS extends TreeBasedVirtualFileSystem<AbstractDatasourceManagerNode> {

   /**
    * 
    */
   private static final long serialVersionUID = -7186418223163754943L;

   private static final String FILESYSTEM_NAME = "datasources";

   @Inject
   public DatasourceManagerVFS(Provider<DatasourceService> datasourceServiceProvider) {
      super(datasourceServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected String doGetNodeName(AbstractDatasourceManagerNode node) {
      if (node instanceof DatasourceFolder)
         return ((DatasourceFolder) node).getName();
      else
         return ((DatasourceDefinition) node).getName();
   }

   @Override
   protected void doRename(AbstractDatasourceManagerNode node, String name) {
      if (node instanceof DatasourceFolder)
         ((DatasourceFolder) node).setName(name);
      else
         ((DatasourceDefinition) node).setName(name);
   }

   @Override
   protected AbstractDatasourceManagerNode instantiateFolder(String folder) {
      return new DatasourceFolder(folder);
   }

   @Override
   protected boolean isFolder(AbstractDatasourceManagerNode node) {
      return node instanceof DatasourceFolder;
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractDatasourceManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof DatasourceFolder))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
