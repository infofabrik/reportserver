package net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;

public interface FileExportExternalEntryProviderHook extends Hook {

   void createMenuEntry(Menu menu, FileServerTreeManagerDao treeHandler);

   boolean isAvailable();

}
