package net.datenwerke.gf.client.upload;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.client.upload.rpc.FileUploadRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;

public class FileUploadServiceDao extends Dao {

   FileUploadRpcServiceAsync rpcService;

   @Inject
   public FileUploadServiceDao(FileUploadRpcServiceAsync fileUploadRpcServiceAsync) {
      this.rpcService = fileUploadRpcServiceAsync;
   }

   public Request uploadInterimFile(FileToUpload file, AsyncCallback<UploadResponse> callback) {
      return rpcService.uploadInterimFile(file, callback);
   }

}
