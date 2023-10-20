package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.service.datasinkmanager.vfs.DatasinkManagerVFS;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class DatasinkOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final DatasinkTreeService datasinkTreeService;
   private final Provider<DatasinkManagerVFS> datasinkManagerVFSProvider;

   @Inject
   public DatasinkOpenTerminalHooker(DatasinkTreeService datasinkTreeService, Provider<DatasinkManagerVFS> datasinkManagerVFSProvider) {
      this.datasinkTreeService = datasinkTreeService;
      this.datasinkManagerVFSProvider = datasinkManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return datasinkTreeService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractDatasinkManagerNode getNode(Long nodeId) {
      return datasinkTreeService.getNodeById(nodeId);
   }
   
   @Override
   public DatasinkManagerVFS getVfs() {
      return datasinkManagerVFSProvider.get();      
   }

}
