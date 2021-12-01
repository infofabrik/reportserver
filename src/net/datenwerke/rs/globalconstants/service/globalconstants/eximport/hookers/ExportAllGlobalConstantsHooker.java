package net.datenwerke.rs.globalconstants.service.globalconstants.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.entity.EntityExportItemConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.globalconstants.service.globalconstants.GlobalConstantsService;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;

import com.google.inject.Inject;

public class ExportAllGlobalConstantsHooker implements ExportAllHook {

	private final GlobalConstantsService service;
	
	@Inject
	public ExportAllGlobalConstantsHooker(GlobalConstantsService service) {
		this.service = service;
	}

	@Override
	public void configure(ExportConfig config) {
		for(GlobalConstant constant : service.getAllGlobalConstants())
			config.addItemConfig(new EntityExportItemConfig(constant));
	}
}
