package net.datenwerke.rs.eximport.service.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsExImportStartup {

	@Inject
	public GenRightsExImportStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(ExportSecurityTarget.class);
		securityService.registerSecurityTarget(ImportSecurityTarget.class);
	}
}
