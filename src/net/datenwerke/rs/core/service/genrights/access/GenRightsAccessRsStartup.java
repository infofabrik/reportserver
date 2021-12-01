package net.datenwerke.rs.core.service.genrights.access;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsAccessRsStartup {

	@Inject
	public GenRightsAccessRsStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(AccessRsSecurityTarget.class);
	}
}
