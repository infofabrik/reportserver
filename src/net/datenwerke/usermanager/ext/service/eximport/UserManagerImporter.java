package net.datenwerke.usermanager.ext.service.eximport;

import com.google.inject.Inject;

import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class UserManagerImporter extends TreeNodeImporter {

	public static final String IMPORTER_ID = "UserManagerImporter";
	
	private final UserManagerService userManager;
	
	@Inject
	public UserManagerImporter(UserManagerService userManager) {
		super();
		this.userManager = userManager;
	}

	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{UserManagerExporter.class};
	}
	
	@Override
	protected TreeDBManager getTreeDBManager() {
		return userManager;
	}
	
	@Override
	public String getId() {
		return IMPORTER_ID;
	}

}
