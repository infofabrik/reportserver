package net.datenwerke.rs.scripting.client.scripting.rpc;

import java.util.HashMap;
import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("scripting")
public interface ScriptingRpcService extends RemoteService {
	
	public CommandResultDto executeScript(String scriptLocation, String args, HashMap<String, String> context) throws ServerCallFailedException;
	
	public List<CommandResultDto> executeLoginScript() throws ServerCallFailedException;
	
	public List<String> getImportPathFor(String line) throws ServerCallFailedException;
}
