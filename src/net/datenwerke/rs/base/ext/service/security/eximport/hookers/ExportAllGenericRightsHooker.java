package net.datenwerke.rs.base.ext.service.security.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.entity.EntityExportItemConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;

import com.google.inject.Inject;

public class ExportAllGenericRightsHooker implements ExportAllHook {

	private final SecurityService securityService;
	
	@Inject
	public ExportAllGenericRightsHooker(
		SecurityService securityService
		) {
		this.securityService = securityService;
	}

	@Override
	public void configure(ExportConfig config) {
		for(Class<?> targetType : securityService.getRegisteredGenericSecurityTargets()){
			GenericSecurityTargetEntity entity = securityService.loadGenericTarget(targetType);
			config.addItemConfig(new EntityExportItemConfig(entity));
		}
	}

}
