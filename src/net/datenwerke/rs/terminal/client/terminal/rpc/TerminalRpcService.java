package net.datenwerke.rs.terminal.client.terminal.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("terminal")
public interface TerminalRpcService extends RemoteService {
	
	public void closeSession(String sessionId) throws ServerCallFailedException;
	
	public String initSession() throws ServerCallFailedException;
	
	public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition) throws ServerCallFailedException, ExpectedException;
	
	public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition, boolean forceResult) throws ServerCallFailedException, ExpectedException;
	
	public CommandResultDto execute(String sessionId, String command) throws ServerCallFailedException, ExpectedException;
	
	public CommandResultDto ctrlCPressed(String sessionId) throws ServerCallFailedException, ExpectedException;
	
	
}
