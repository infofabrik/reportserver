package net.datenwerke.rs.adminutils.client.suuser;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.suuser.rpc.SuUserRpcServiceAsync;

public class SuUserDao extends Dao {

   private final SuUserRpcServiceAsync rpcService;

   @Inject
   public SuUserDao(SuUserRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void su(String username, AsyncCallback<Void> callback) {
      rpcService.su(username, transformAndKeepCallback(callback));
   }

   public void su(Long id, RsAsyncCallback<Void> callback) {
      rpcService.su(id, transformAndKeepCallback(callback));
   }

}
