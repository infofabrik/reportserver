package net.datenwerke.rs.pkg.client.pkg.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;

@RemoteServiceRelativePath("pkg")
public interface PkgRpcService extends RemoteService {

   public String uploadAndExecute(FileServerFolderDto folder, FileToUpload fileToUpload)
         throws ServerCallFailedException;

}