package net.datenwerke.security.service.genrights.security;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsSecurityStartup {

	@Inject
	public GenRightsSecurityStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(GenericSecurityTargetAdminViewSecurityTarget.class);
	}
}
