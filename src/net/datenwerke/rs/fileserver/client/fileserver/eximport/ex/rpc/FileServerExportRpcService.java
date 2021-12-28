package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;

@RemoteServiceRelativePath("fileserver_export")
public interface FileServerExportRpcService extends RemoteService {

	public void quickExport(AbstractFileServerNodeDto dto) throws ServerCallFailedException;
	
	public String loadResult() throws ServerCallFailedException;
}
