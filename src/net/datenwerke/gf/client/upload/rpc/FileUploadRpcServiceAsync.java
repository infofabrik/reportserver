package net.datenwerke.gf.client.upload.rpc;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadResponse;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface FileUploadRpcServiceAsync {

	Request uploadInterimFile(FileToUpload file, 
			AsyncCallback<UploadResponse> callback);

	

}
