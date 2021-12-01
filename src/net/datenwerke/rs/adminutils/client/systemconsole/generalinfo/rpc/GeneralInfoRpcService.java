package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;

@RemoteServiceRelativePath("generalinfoconsole")
public interface GeneralInfoRpcService extends RemoteService {

	GeneralInfoDto loadGeneralInfo() throws ServerCallFailedException;

}
