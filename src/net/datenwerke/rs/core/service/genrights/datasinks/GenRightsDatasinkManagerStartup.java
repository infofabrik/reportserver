package net.datenwerke.rs.core.service.genrights.datasinks;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsDatasinkManagerStartup {

	@Inject
	public GenRightsDatasinkManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(DatasinkManagerAdminViewSecurityTarget.class);
	}
}
