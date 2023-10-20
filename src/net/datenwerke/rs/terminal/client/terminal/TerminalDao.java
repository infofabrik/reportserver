package net.datenwerke.rs.terminal.client.terminal;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.rpc.TerminalRpcServiceAsync;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TerminalDao extends Dao {

   private final TerminalRpcServiceAsync rpcService;
   private String sessionId;
   private String pathWay;

   @Inject
   public TerminalDao(TerminalRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void init(AbstractNodeDto node, Dto2PosoMapper dto2PosoMapper, final RsAsyncCallback<HashMap<String, String>> callback) {
      rpcService.initSession(node, dto2PosoMapper, transformAndKeepCallback(new RsAsyncCallback<HashMap<String, String>>() {
         @Override
         public void onSuccess(HashMap<String, String> result) {
            sessionId = result.get("sessionId");
            pathWay = result.get("pathWay");
            callback.onSuccess(result);
         }
      }));
   }

   public String getSessionId() {
      return sessionId;
   }

   public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
   }

   public void autocomplete(String command, int cursorPosition, AsyncCallback<AutocompleteResultDto> callback) {
      if (null == sessionId)
         throw new IllegalArgumentException("Session not initialized");

      DaoAsyncCallback<AutocompleteResultDto> daoCallback = transformAndKeepCallback(callback);
      daoCallback.ignoreExpectedExceptions(true);
      rpcService.autocomplete(sessionId, command, cursorPosition, daoCallback);
   }

   public void autocomplete(String command, int cursorPosition, boolean forceResult,
         AsyncCallback<AutocompleteResultDto> callback) {
      if (null == sessionId)
         throw new IllegalArgumentException("Session not initialized");

      DaoAsyncCallback<AutocompleteResultDto> daoCallback = transformAndKeepCallback(callback);
      daoCallback.ignoreExpectedExceptions(true);
      rpcService.autocomplete(sessionId, command, cursorPosition, forceResult, daoCallback);
   }

   public void execute(String command, AsyncCallback<CommandResultDto> callback) {
      if (null == sessionId)
         throw new IllegalArgumentException("Session not initialized");

      DaoAsyncCallback<CommandResultDto> daoCallback = transformAndKeepCallback(callback);
      daoCallback.ignoreExpectedExceptions(true);
      rpcService.execute(sessionId, command, daoCallback);
   }

   public void ctrlCPressed(AsyncCallback<CommandResultDto> callback) {
      if (null == sessionId)
         throw new IllegalArgumentException("Session not initialized");

      DaoAsyncCallback<CommandResultDto> daoCallback = transformAndKeepCallback(callback);
      daoCallback.ignoreExpectedExceptions(true);
      rpcService.ctrlCPressed(sessionId, daoCallback);
   }

   public void closeSession() {
      sessionId = null;
      rpcService.closeSession(sessionId, new RsAsyncCallback<Void>());
   }

   public String getPathWay() {
      return pathWay;
   }

   public void setPathWay(String pathWay) {
      this.pathWay = pathWay;
   }

}
