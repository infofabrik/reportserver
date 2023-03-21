package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("generalinfoconsole")
public interface GeneralInfoRpcService extends RemoteService {

   String loadGeneralInfo() throws ServerCallFailedException;

}
