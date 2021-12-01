package net.datenwerke.rs.adminutils.service.su.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsSuStartup {

	@Inject
	public GenRightsSuStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(SuSecurityTarget.class);
	}
}
