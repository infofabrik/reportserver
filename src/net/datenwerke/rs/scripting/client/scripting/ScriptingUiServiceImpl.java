package net.datenwerke.rs.scripting.client.scripting;

import java.util.HashMap;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

import com.google.inject.Inject;

public class ScriptingUiServiceImpl implements ScriptingUiService {

	private final ScriptingDao scriptingDao;
	private final TerminalUIService terminalService;
		
	@Inject
	public ScriptingUiServiceImpl(
		ScriptingDao scriptingDao,
		TerminalUIService terminalService	
		){
		
		/* store objects */
		this.terminalService = terminalService;
		this.scriptingDao = scriptingDao;
	}

	@Override
	public void executeScript(String scriptLocation, String args, HashMap<String, String> config) {
		scriptingDao.executeScript(scriptLocation, args, config, new RsAsyncCallback<CommandResultDto>(){
			@Override
			public void onSuccess(CommandResultDto result) {
				terminalService.processExternalResult(result);
			}
		});
	}
}
