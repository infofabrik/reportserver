package net.datenwerke.rs.scripting.client.scripting;

import java.util.HashMap;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.scripting.client.scripting.rpc.ScriptingRpcServiceAsync;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class ScriptingDao extends Dao {

   private final ScriptingRpcServiceAsync rpcService;

   @Inject
   public ScriptingDao(ScriptingRpcServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public void executeScript(String scriptLocation, String args, HashMap<String, String> context,
         AsyncCallback<CommandResultDto> callback) {
      rpcService.executeScript(scriptLocation, args, context, transformAndKeepCallback(callback));
   }

   public void executeLoginScript(AsyncCallback<List<CommandResultDto>> callback) {
      rpcService.executeLoginScript(transformAndKeepCallback(callback));
   }

   public void getImportPathFor(String token, AsyncCallback<List<String>> callback) {
      rpcService.getImportPathFor(token, transformAndKeepCallback(callback));
   }
}
