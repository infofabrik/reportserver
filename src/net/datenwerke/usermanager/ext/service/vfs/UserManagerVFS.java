package net.datenwerke.usermanager.ext.service.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserManagerVFS extends TreeBasedVirtualFileSystem<AbstractUserManagerNode> {

   /**
    * 
    */
   private static final long serialVersionUID = 1891427771226007347L;

   private static final String FILESYSTEM_NAME = "usermanager";

   @Inject
   public UserManagerVFS(Provider<UserManagerService> userServiceProvider) {
      super(userServiceProvider);
   }

   @Override
   public String getFileSystemName() {
      return FILESYSTEM_NAME;
   }

   @Override
   protected String doGetNodeName(AbstractUserManagerNode node) {
      if (node instanceof OrganisationalUnit)
         return ((OrganisationalUnit) node).getName();
      else if (node instanceof Group)
         return ((Group) node).getName();
      else
         return ((User) node).getUsername();
   }

   @Override
   protected void doRename(AbstractUserManagerNode node, String name) {
      if (node instanceof OrganisationalUnit)
         ((OrganisationalUnit) node).setName(name);
      else if (node instanceof Group)
         ((Group) node).setName(name);
      else
         ((User) node).setUsername(name);
   }

   @Override
   protected AbstractUserManagerNode instantiateFolder(String folder) {
      return new OrganisationalUnit(folder);
   }

   @Override
   protected boolean isFolder(AbstractUserManagerNode node) {
      return node instanceof OrganisationalUnit;
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      if (!vfsLocation.exists()) {
         VFSLocation parentLoc = vfsLocation.getParentLocation();
         if (!parentLoc.exists())
            return false;

         AbstractUserManagerNode parent = getNodeByLocation(parentLoc);
         if (!(parent instanceof OrganisationalUnit))
            return false;

         return canWrite(parent);
      } else {
         return false;
      }
   }

}
