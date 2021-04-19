package net.datenwerke.rs.terminal.client.terminal.rpc;

import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TerminalRpcServiceAsync {

	void autocomplete(String sessionId, String command, int cursorPosition,
			AsyncCallback<AutocompleteResultDto> callback);

	void autocomplete(String sessionId, String command, int cursorPosition, boolean forceResult,
			AsyncCallback<AutocompleteResultDto> callback);

	void execute(String sessionId, String command, AsyncCallback<CommandResultDto> callback);

	void initSession(AsyncCallback<String> callback);

	void closeSession(String sessionId, AsyncCallback<Void> callback);

	void ctrlCPressed(String sessionId, AsyncCallback<CommandResultDto> callback);

}
