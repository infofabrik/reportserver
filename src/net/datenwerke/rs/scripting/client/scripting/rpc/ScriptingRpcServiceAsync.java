package net.datenwerke.rs.scripting.client.scripting.rpc;

import java.util.HashMap;
import java.util.List;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ScriptingRpcServiceAsync {

	void executeScript(String scriptLocation, String args, HashMap<String, String> context,
			AsyncCallback<CommandResultDto> callback);

	void executeLoginScript(AsyncCallback<List<CommandResultDto>> callback);

	void getImportPathFor(String line, AsyncCallback<List<String>> callback);


}
