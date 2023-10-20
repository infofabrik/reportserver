package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.service.datasourcemanager.vfs.DatasourceManagerVFS;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class DatasorceOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final DatasourceService datasourceService;
   private final Provider<DatasourceManagerVFS> datasourceManagerVFSProvider;

   @Inject
   public DatasorceOpenTerminalHooker(DatasourceService datasourceService, Provider<DatasourceManagerVFS> datasourceManagerVFSProvider) {
      this.datasourceService = datasourceService;
      this.datasourceManagerVFSProvider = datasourceManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return datasourceService.getBaseType().getName().equals(type);
   }

   @Override
   public AbstractDatasourceManagerNode getNode(Long nodeId) {
      return datasourceService.getNodeById(nodeId);
   }
   
   @Override
   public DatasourceManagerVFS getVfs() {
      return datasourceManagerVFSProvider.get();            
   }

}
