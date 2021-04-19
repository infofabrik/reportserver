package net.datenwerke.security.service.genrights.usermanager;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsUserManagerStartup {

	@Inject
	public GenRightsUserManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(UserManagerAdminViewSecurityTarget.class);
	}
}
