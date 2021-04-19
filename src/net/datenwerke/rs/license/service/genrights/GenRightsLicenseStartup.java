package net.datenwerke.rs.license.service.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsLicenseStartup {

	@Inject
	public GenRightsLicenseStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(LicenseSecurityTarget.class);
	}
}
