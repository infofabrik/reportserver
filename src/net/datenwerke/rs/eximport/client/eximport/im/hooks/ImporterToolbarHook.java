package net.datenwerke.rs.eximport.client.eximport.im.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface ImporterToolbarHook extends Hook {

	void enhance(ToolBar mainToolbar, ImportMainPanel importMainPanel);
	
}
