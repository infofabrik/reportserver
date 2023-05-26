package net.datenwerke.rs.pkg.client.pkg;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.pkg.client.pkg.rpc.PkgRpcServiceAsync;

public class PkgDao extends Dao {

   private final PkgRpcServiceAsync rpcService;

   @Inject
   public PkgDao(
         PkgRpcServiceAsync rpcService
         ) {
      this.rpcService = rpcService;
   }

   public void uploadAndExecute(FileServerFolderDto folder, FileToUpload fileToUpload,
         AsyncCallback<String> callback) {
      rpcService.uploadAndExecute(folder, fileToUpload, transformAndKeepCallback(callback));
   }
}
