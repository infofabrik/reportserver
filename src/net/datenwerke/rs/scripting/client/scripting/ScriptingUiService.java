package net.datenwerke.rs.scripting.client.scripting;

import java.util.HashMap;

public interface ScriptingUiService {
	
	public static final String REPORTSERVER_EVENT_AFTER_EXECUTE_LOGIN_SCRIPT = "REPORTSERVER_EVENT_AFTER_EXECUTE_LOGIN_SCRIPT"; //$NON-NLS-1$

	void executeScript(String scriptLocation, String args, HashMap<String, String> config);

}
