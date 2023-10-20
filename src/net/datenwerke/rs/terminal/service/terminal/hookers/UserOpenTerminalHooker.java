package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.usermanager.ext.service.vfs.UserManagerVFS;

public class UserOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final UserManagerService userManagerService;
   private final Provider<UserManagerVFS> userManagerVFSProvider;

   @Inject
   public UserOpenTerminalHooker(UserManagerService userManagerService, Provider<UserManagerVFS> userManagerVFSProvider) {
      this.userManagerService = userManagerService;
      this.userManagerVFSProvider = userManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return userManagerService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractUserManagerNode getNode(Long nodeId) {
      return userManagerService.getNodeById(nodeId);
   }
   
   @Override
   public UserManagerVFS getVfs() {
      return userManagerVFSProvider.get();      
   }

}
