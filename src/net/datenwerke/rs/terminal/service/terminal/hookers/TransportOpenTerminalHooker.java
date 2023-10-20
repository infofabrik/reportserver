package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.vfs.TransportManagerVFS;

public class TransportOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final TransportTreeService transportTreeService;
   private final Provider<TransportManagerVFS> transportManagerVFSProvider;

   @Inject
   public TransportOpenTerminalHooker(TransportTreeService transportTreeService, Provider<TransportManagerVFS> transportManagerVFSProvider) {
      this.transportTreeService = transportTreeService;
      this.transportManagerVFSProvider = transportManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return transportTreeService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractTransportManagerNode getNode(Long nodeId) {
      return transportTreeService.getNodeById(nodeId);
   }
   
   @Override
   public TransportManagerVFS getVfs() {
      return transportManagerVFSProvider.get();      
   }

}
