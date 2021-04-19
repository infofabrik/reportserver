package net.datenwerke.gf.client.upload.rpc;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fileuploadservice")
public interface FileUploadRpcService extends RemoteService {

	UploadResponse uploadInterimFile(FileToUpload file) throws ServerCallFailedException;
	
	
}
