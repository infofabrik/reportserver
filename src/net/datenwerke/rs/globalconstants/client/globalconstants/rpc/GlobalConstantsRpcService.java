package net.datenwerke.rs.globalconstants.client.globalconstants.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;

@RemoteServiceRelativePath("globalconstants")
public interface GlobalConstantsRpcService extends RemoteService {

   public List<GlobalConstantDto> loadGlobalConstants();

   public GlobalConstantDto addNewConstant();

   public GlobalConstantDto updateConstant(GlobalConstantDto constantDto) throws ServerCallFailedException;

   public void removeConstants(Collection<GlobalConstantDto> constant);

   public void removeAllConstants();
}
