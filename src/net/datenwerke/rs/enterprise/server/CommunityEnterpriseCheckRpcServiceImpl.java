package net.datenwerke.rs.enterprise.server;

import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.enterprise.client.EnterpriseInformationDto;
import net.datenwerke.rs.enterprise.client.rpc.EnterpriseCheckRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

@Singleton
public class CommunityEnterpriseCheckRpcServiceImpl extends SecuredRemoteServiceServlet implements EnterpriseCheckRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@SecurityChecked(bypass=true)
	public EnterpriseInformationDto getEnterpriseInfos() throws ServerCallFailedException {
		EnterpriseInformationDto info = new EnterpriseInformationDto();
		return info;
	}

}
