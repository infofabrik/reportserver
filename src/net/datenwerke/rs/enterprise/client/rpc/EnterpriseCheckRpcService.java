package net.datenwerke.rs.enterprise.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.enterprise.client.EnterpriseInformationDto;

@RemoteServiceRelativePath("enterprisecheck")
public interface EnterpriseCheckRpcService extends RemoteService {

	EnterpriseInformationDto getEnterpriseInfos() throws ServerCallFailedException;

}
