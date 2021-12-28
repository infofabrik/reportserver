package net.datenwerke.rs.core.client.contexthelp;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.contexthelp.rpc.ContextHelpRpcServiceAsync;

public class ContextHelpDao extends Dao {

   private final ContextHelpRpcServiceAsync asyncService;

   @Inject
   public ContextHelpDao(ContextHelpRpcServiceAsync asyncService) {
      this.asyncService = asyncService;
   }

   public void getContextHelp(ContextHelpInfo info, RsAsyncCallback<String> callback) {
      asyncService.getContextHelp(info, transformAndKeepCallback(callback));
   }
}
