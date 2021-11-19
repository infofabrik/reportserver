package net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.helper.menu.FileSendToMenuItem;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;

public interface FileExportExternalEntryProviderHook extends Hook {

   FileSendToMenuItem getMenuEntry(Menu menu, FileServerTreeManagerDao treeHandler);

   boolean isAvailable();

}
