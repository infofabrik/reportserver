package net.datenwerke.gf.service.fileselection.hooks;

import java.util.ArrayList;
import java.util.Map;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface FileSelectionHandlerHook extends Hook {

	boolean consumes(String handler);

	void processData(ArrayList<SelectedFileWrapper> data, String handler, Map<String, String> hashMap);

}
