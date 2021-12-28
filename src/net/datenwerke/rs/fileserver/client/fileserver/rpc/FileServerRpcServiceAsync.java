package net.datenwerke.rs.fileserver.client.fileserver.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;

public interface FileServerRpcServiceAsync {

   void updateFile(FileServerFileDto file, String data, AsyncCallback<Void> callback);

   void loadFileDataAsString(FileServerFileDto file, AsyncCallback<String> callback);

   void uploadFiles(FileServerFolderDto folder, List<FileToUpload> files,
         AsyncCallback<List<FileServerFileDto>> callback);

   void uploadAndExtract(FileServerFolderDto folder, FileToUpload fileToUpload,
         AsyncCallback<List<AbstractFileServerNodeDto>> callback);

}
