package net.datenwerke.rs.uservariables.service.uservariables.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.entity.EntityExportItemConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

import com.google.inject.Inject;

public class ExportAllUserVariablesHooker implements ExportAllHook {

	private final UserVariableService service;
	
	@Inject
	public ExportAllUserVariablesHooker(UserVariableService teamSpaceService) {
		this.service = teamSpaceService;
	}

	@Override
	public void configure(ExportConfig config) {
		for(UserVariableDefinition def : service.getDefinedVariableDefinitions()){
			config.addItemConfig(new EntityExportItemConfig(def));
			for(UserVariableInstance inst : service.getInstancesForDefinition(def))
				config.addItemConfig(new EntityExportItemConfig(inst));
		}
	}

}
