package net.datenwerke.rs.passwordpolicy.client.accountinhibition;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.dto.InhibitionState;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.rpc.AccountInhibitionRpcServiceAsync;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class AccountInhibitionServiceDao extends Dao {

   private final AccountInhibitionRpcServiceAsync rpcService;

   @Inject
   public AccountInhibitionServiceDao(AccountInhibitionRpcServiceAsync accountInhibitionRpcServiceAsync) {
      super();
      rpcService = accountInhibitionRpcServiceAsync;
   }

   public void getInhibitionState(UserDto user, AsyncCallback<InhibitionState> callback) {
      rpcService.getInhibitionState(user, transformAndKeepCallback(callback));
   }

   public void applyAccountInhibitionConfiguration(AccountInhibitionConfiguration accountInhibitionConfiguration,
         AsyncCallback<Void> callback) {
      rpcService.applyAccountInhibitionConfiguration(accountInhibitionConfiguration,
            transformAndKeepCallback(callback));
   }

   public void getAccountInhibitionConfiguration(UserDto user, AsyncCallback<AccountInhibitionConfiguration> callback) {
      rpcService.getAccountInhibitionConfiguration(user, transformAndKeepCallback(callback));
   }
}
