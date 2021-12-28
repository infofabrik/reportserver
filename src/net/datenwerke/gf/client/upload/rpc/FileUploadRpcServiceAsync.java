package net.datenwerke.gf.client.upload.rpc;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadResponse;

public interface FileUploadRpcServiceAsync {

   Request uploadInterimFile(FileToUpload file, AsyncCallback<UploadResponse> callback);

}
