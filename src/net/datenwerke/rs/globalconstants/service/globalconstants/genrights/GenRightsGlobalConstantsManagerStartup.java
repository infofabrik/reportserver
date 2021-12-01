package net.datenwerke.rs.globalconstants.service.globalconstants.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsGlobalConstantsManagerStartup {

	@Inject
	public GenRightsGlobalConstantsManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(GlobalConstantsAdminViewSecurityTarget.class);
	}
}
