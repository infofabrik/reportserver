package net.datenwerke.rs.core.service.genrights.datasinks;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsDatasinkManagerStartup {

	@Inject
	public GenRightsDatasinkManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(DatasinkManagerAdminViewSecurityTarget.class);
	}
}
