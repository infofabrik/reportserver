package net.datenwerke.rs.adminutils.server.systemconsole.generalinfo;

import com.google.inject.Singleton;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc.GeneralInfoRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class GeneralInfoRpcDummyServiceImpl extends SecuredRemoteServiceServlet implements GeneralInfoRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1809584778778017356L;

	@Override
	public GeneralInfoDto loadGeneralInfo() {
		return null;
	}

}
