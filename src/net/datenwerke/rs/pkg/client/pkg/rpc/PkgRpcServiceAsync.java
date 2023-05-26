package net.datenwerke.rs.pkg.client.pkg.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;

public interface PkgRpcServiceAsync {

   void uploadAndExecute(FileServerFolderDto folder, FileToUpload fileToUpload,
         AsyncCallback<String> callback);

}
