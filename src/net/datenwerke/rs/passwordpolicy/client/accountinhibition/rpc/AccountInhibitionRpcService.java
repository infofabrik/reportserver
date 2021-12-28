package net.datenwerke.rs.passwordpolicy.client.accountinhibition.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.inject.name.Named;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionConfiguration;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.dto.InhibitionState;
import net.datenwerke.security.client.usermanager.dto.UserDto;

@RemoteServiceRelativePath("security_accountinhibition")
public interface AccountInhibitionRpcService extends RemoteService {
   public InhibitionState getInhibitionState(UserDto user) throws ServerCallFailedException;

   public void applyAccountInhibitionConfiguration(AccountInhibitionConfiguration accountInhibitionModel)
         throws ServerCallFailedException;

   public AccountInhibitionConfiguration getAccountInhibitionConfiguration(@Named("user") UserDto user)
         throws ServerCallFailedException;
}
