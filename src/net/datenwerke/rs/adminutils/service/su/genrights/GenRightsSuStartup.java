package net.datenwerke.rs.adminutils.service.su.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsSuStartup {

	@Inject
	public GenRightsSuStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(SuSecurityTarget.class);
	}
}
