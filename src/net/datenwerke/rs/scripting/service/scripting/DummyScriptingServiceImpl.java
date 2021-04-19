package net.datenwerke.rs.scripting.service.scripting;

import java.util.Map;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;

public class DummyScriptingServiceImpl implements SimpleScriptingService {


	@Override
	public ScriptResult executeScript(String join, TerminalSession session, Map<String, Object> objectMap,
			String arguments) throws Exception {
		return null;
	}

	@Override
	public ScriptResult executeScript(FileServerFile script, TerminalSession session, Map<String, Object> objectMap,
			String arguments) {
		return null;
	}

}
