package net.datenwerke.rs.scripting.service.scripting;

import java.util.Map;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;

/**
 * Dummy Interface
 *
 */
@ImplementedBy(DummyScriptingServiceImpl.class)
public interface SimpleScriptingService {

	public ScriptResult executeScript(String join, TerminalSession session,
			Map<String, Object> objectMap, String arguments) throws Exception;

	public ScriptResult executeScript(FileServerFile script, TerminalSession session, Map<String, Object> objectMap,
			String arguments) throws Exception;

}
