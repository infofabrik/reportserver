package net.datenwerke.gf.service.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsAdministrationStartup {

	@Inject
	public GenRightsAdministrationStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(AdministrationViewSecurityTarget.class);
	}
}
