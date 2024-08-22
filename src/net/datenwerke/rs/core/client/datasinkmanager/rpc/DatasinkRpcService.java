package net.datenwerke.rs.core.client.datasinkmanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

@RemoteServiceRelativePath("datasinks_service")
public interface DatasinkRpcService extends RemoteService {

   String getDefaultFolder(DatasinkDefinitionDto datasinkDto) throws ServerCallFailedException;
}
