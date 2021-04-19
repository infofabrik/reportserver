package net.datenwerke.rs.uservariables.service.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class ClientUserVariableStartup {

	@Inject
	public ClientUserVariableStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(UserVariableAdminViewSecurityTarget.class);
	}
}
