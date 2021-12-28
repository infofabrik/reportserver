package net.datenwerke.usermanager.ext.service.eximport.hooker;

import com.google.inject.Inject;

import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.ImportAllNodesHooker;
import net.datenwerke.usermanager.ext.service.eximport.UserManagerExporter;

public class ImportAllUsersHooker extends ImportAllNodesHooker<AbstractUserManagerNode>{

	@Inject
	public ImportAllUsersHooker(
		UserManagerService treeDbManager
		) {
		super(treeDbManager, UserManagerExporter.class);
	}

}
