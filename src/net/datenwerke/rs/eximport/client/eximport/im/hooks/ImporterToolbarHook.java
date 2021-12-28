package net.datenwerke.rs.eximport.client.eximport.im.hooks;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;

public interface ImporterToolbarHook extends Hook {

	void enhance(ToolBar mainToolbar, ImportMainPanel importMainPanel);
	
}
