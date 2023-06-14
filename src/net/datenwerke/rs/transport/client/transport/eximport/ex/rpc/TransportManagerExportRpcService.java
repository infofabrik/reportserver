package net.datenwerke.rs.transport.client.transport.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;

@RemoteServiceRelativePath("transportmanager_export")
public interface TransportManagerExportRpcService extends RemoteService {
   public void quickExport(AbstractTransportManagerNodeDto dto) throws ServerCallFailedException;

   public String loadResult() throws ServerCallFailedException;
}