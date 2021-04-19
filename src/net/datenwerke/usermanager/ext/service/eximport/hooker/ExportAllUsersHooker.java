package net.datenwerke.usermanager.ext.service.eximport.hooker;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExImportOptions;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporterConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import com.google.inject.Inject;

public class ExportAllUsersHooker implements ExportAllHook {

	private final UserManagerService userService;
	
	@Inject
	public ExportAllUsersHooker(UserManagerService userService) {
		this.userService = userService;
	}

	@Override
	public void configure(ExportConfig config) {
		TreeNodeExporterConfig specConfig = new TreeNodeExporterConfig();
		specConfig.addExImporterOptions(TreeNodeExImportOptions.INCLUDE_OWNER, TreeNodeExImportOptions.INCLUDE_SECURITY);
		config.addSpecificExporterConfigs(specConfig);
		
		for(AbstractNode<?> node : userService.getAllNodes())
			config.addItemConfig(new TreeNodeExportItemConfig(node));
	}

}
