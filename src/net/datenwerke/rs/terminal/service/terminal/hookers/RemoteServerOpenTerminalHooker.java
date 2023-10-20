package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.vfs.RemoteServerManagerVFS;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class RemoteServerOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final RemoteServerTreeService remoteServerTreeService;
   private final Provider<RemoteServerManagerVFS> remoteServerManagerVFSProvider;

   @Inject
   public RemoteServerOpenTerminalHooker(RemoteServerTreeService remoteServerTreeService, Provider<RemoteServerManagerVFS> remoteServerManagerVFSProvider) {
      this.remoteServerTreeService = remoteServerTreeService;
      this.remoteServerManagerVFSProvider = remoteServerManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return remoteServerTreeService.getBaseType().getName().equals(type);
   }

   @Override
   public AbstractRemoteServerManagerNode getNode(Long nodeId) {
      return remoteServerTreeService.getNodeById(nodeId);
   }
   
   @Override
   public RemoteServerManagerVFS getVfs() {
      return remoteServerManagerVFSProvider.get();            
   }

}
