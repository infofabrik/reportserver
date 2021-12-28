package net.datenwerke.rs.globalconstants.service.globalconstants.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsGlobalConstantsManagerStartup {

	@Inject
	public GenRightsGlobalConstantsManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(GlobalConstantsAdminViewSecurityTarget.class);
	}
}
