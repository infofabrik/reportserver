package net.datenwerke.rs.fileserver.client.fileserver;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerRpcServiceAsync;

public class FileServerDao extends Dao {

   private final FileServerRpcServiceAsync rpcService;

   @Inject
   public FileServerDao(FileServerRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void updateFile(FileServerFileDto file, String data, AsyncCallback<Void> callback) {
      rpcService.updateFile(file, data, transformAndKeepCallback(callback));
   }

   public void loadFileDataAsString(FileServerFileDto file, AsyncCallback<String> callback) {
      rpcService.loadFileDataAsString(file, transformAndKeepCallback(callback));
   }

   public void uploadFiles(FileServerFolderDto folder, List<FileToUpload> files,
         AsyncCallback<List<FileServerFileDto>> callback) {
      rpcService.uploadFiles(folder, files, transformListCallback(callback));
   }

   public void uploadAndExtract(FileServerFolderDto folder, FileToUpload fileToUpload,
         AsyncCallback<List<AbstractFileServerNodeDto>> callback) {
      rpcService.uploadAndExtract(folder, fileToUpload, transformListCallback(callback));
   }
}
