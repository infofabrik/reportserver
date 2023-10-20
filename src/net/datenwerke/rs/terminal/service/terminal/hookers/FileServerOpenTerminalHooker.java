package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class FileServerOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final FileServerService fileServerService;
   private final Provider<FileServerVfs> fileServerVfsProvider;

   @Inject
   public FileServerOpenTerminalHooker(FileServerService fileServerService, Provider<FileServerVfs> fileServerVfsProvider) {
      this.fileServerService = fileServerService;
      this.fileServerVfsProvider = fileServerVfsProvider;
   }

   @Override
   public boolean consumes(String type) {
      return fileServerService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractFileServerNode getNode(Long nodeId) {
      return fileServerService.getNodeById(nodeId);
   }
   
   @Override
   public FileServerVfs getVfs() {
      return fileServerVfsProvider.get();      
   }

}
