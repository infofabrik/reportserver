package net.datenwerke.rs.terminal.client.terminal.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface TerminalRpcServiceAsync {

   void autocomplete(String sessionId, String command, int cursorPosition,
         AsyncCallback<AutocompleteResultDto> callback);

   void autocomplete(String sessionId, String command, int cursorPosition, boolean forceResult,
         AsyncCallback<AutocompleteResultDto> callback);

   void execute(String sessionId, String command, AsyncCallback<CommandResultDto> callback);

   void initSession(AbstractNodeDto node, Dto2PosoMapper dto2PosoMapper, AsyncCallback<HashMap<String, String>> callback);

   void closeSession(String sessionId, AsyncCallback<Void> callback);

   void ctrlCPressed(String sessionId, AsyncCallback<CommandResultDto> callback);

}
