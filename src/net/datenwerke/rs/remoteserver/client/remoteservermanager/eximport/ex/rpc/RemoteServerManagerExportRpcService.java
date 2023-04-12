package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;

@RemoteServiceRelativePath("remoteservermanager_export")
public interface RemoteServerManagerExportRpcService extends RemoteService {
   public void quickExport(AbstractRemoteServerManagerNodeDto dto) throws ServerCallFailedException;

   public String loadResult() throws ServerCallFailedException;
}