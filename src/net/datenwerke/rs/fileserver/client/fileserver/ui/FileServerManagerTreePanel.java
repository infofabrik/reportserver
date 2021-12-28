package net.datenwerke.rs.fileserver.client.fileserver.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerManagerAdminViewTree;

/**
 * 
 *
 */
public class FileServerManagerTreePanel extends AbstractTreeNavigationPanel {

	@Inject
	public FileServerManagerTreePanel(
		HookHandlerService hookHandler,
		@FileServerManagerAdminViewTree UITree tree
		){
		super(hookHandler, tree);
	}
	
}
