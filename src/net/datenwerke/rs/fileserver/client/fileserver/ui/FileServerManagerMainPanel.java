package net.datenwerke.rs.fileserver.client.fileserver.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class FileServerManagerMainPanel extends AbstractTreeMainPanel {

	@Inject
	public FileServerManagerMainPanel(
		FileServerTreeManagerDao fileServerTreeManager
		){
	
		super(fileServerTreeManager);
	}
	
	@Override
	protected String getToolbarName() {
		return "fileserver:admin:view:toolbar";
	}
}
