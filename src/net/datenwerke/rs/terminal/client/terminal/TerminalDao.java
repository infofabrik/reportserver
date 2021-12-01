package net.datenwerke.rs.terminal.client.terminal;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.rpc.TerminalRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class TerminalDao extends Dao {

	private final TerminalRpcServiceAsync rpcService;
	private String sessionId;

	@Inject
	public TerminalDao(TerminalRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void init(final RsAsyncCallback<String> callback){
		rpcService.initSession(transformAndKeepCallback(new RsAsyncCallback<String>(){
			@Override
			public void onSuccess(String result) {
				sessionId = result;
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

	public void autocomplete(String command, int cursorPosition,
			AsyncCallback<AutocompleteResultDto> callback){
		if(null == sessionId)
			throw new IllegalArgumentException("Session not initialized");
		
		DaoAsyncCallback<AutocompleteResultDto> daoCallback = transformAndKeepCallback(callback);
		daoCallback.ignoreExpectedExceptions(true);
		rpcService.autocomplete(sessionId, command, cursorPosition, daoCallback);
	}

	public void autocomplete(String command, int cursorPosition, boolean forceResult,
			AsyncCallback<AutocompleteResultDto> callback){
		if(null == sessionId)
			throw new IllegalArgumentException("Session not initialized");
		
		DaoAsyncCallback<AutocompleteResultDto> daoCallback = transformAndKeepCallback(callback);
		daoCallback.ignoreExpectedExceptions(true);
		rpcService.autocomplete(sessionId, command, cursorPosition, forceResult, daoCallback);
	}

	public void execute(String command, AsyncCallback<CommandResultDto> callback){
		if(null == sessionId)
			throw new IllegalArgumentException("Session not initialized");
		
		DaoAsyncCallback<CommandResultDto> daoCallback = transformAndKeepCallback(callback);
		daoCallback.ignoreExpectedExceptions(true);
		rpcService.execute(sessionId, command, daoCallback);
	}
	
	public void ctrlCPressed(AsyncCallback<CommandResultDto> callback){
		if(null == sessionId)
			throw new IllegalArgumentException("Session not initialized");
		
		DaoAsyncCallback<CommandResultDto> daoCallback = transformAndKeepCallback(callback);
		daoCallback.ignoreExpectedExceptions(true);
		rpcService.ctrlCPressed(sessionId, daoCallback);
	}

	public void closeSession(){
		sessionId = null;
		rpcService.closeSession(sessionId, new RsAsyncCallback<Void>());
	}
}
