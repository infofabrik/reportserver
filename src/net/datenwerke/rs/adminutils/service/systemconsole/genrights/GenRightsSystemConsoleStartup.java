package net.datenwerke.rs.adminutils.service.systemconsole.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsSystemConsoleStartup {

	@Inject
	public GenRightsSystemConsoleStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(SystemConsoleSecurityTarget.class);
	}
}
