package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo;

import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc.GeneralInfoRpcServiceAsync;

public class GeneralInfoDao extends Dao {

   private final GeneralInfoRpcServiceAsync rpcService;

   @Inject
   public GeneralInfoDao(GeneralInfoRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadGeneralInfo(RsAsyncCallback<String> callback) {
      rpcService.loadGeneralInfo(transformAndKeepCallback(callback));
   }

}
