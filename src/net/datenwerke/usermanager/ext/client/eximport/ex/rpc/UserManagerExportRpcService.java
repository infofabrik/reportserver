package net.datenwerke.usermanager.ext.client.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

@RemoteServiceRelativePath("usermanager_export")
public interface UserManagerExportRpcService extends RemoteService {

   public void quickExport(AbstractUserManagerNodeDto dto) throws ServerCallFailedException;

   public String loadResult() throws ServerCallFailedException;
}
