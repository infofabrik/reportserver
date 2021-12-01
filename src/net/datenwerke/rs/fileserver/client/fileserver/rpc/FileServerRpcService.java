package net.datenwerke.rs.fileserver.client.fileserver.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;

@RemoteServiceRelativePath("fileserver")
public interface FileServerRpcService extends RemoteService{

	public void updateFile(FileServerFileDto file, String data) throws ServerCallFailedException;

	public List<FileServerFileDto> uploadFiles(FileServerFolderDto folder, List<FileToUpload> files)  throws ServerCallFailedException;
	
	String loadFileDataAsString(FileServerFileDto file);

	public List<AbstractFileServerNodeDto> uploadAndExtract(FileServerFolderDto folder, FileToUpload fileToUpload) throws ServerCallFailedException;

}