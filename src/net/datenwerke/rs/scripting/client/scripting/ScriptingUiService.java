package net.datenwerke.rs.scripting.client.scripting;

import java.util.HashMap;

public interface ScriptingUiService {

   void executeScript(String scriptLocation, String args, HashMap<String, String> config);

}
