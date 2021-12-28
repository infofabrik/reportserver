package net.datenwerke.rs.fileserver.service.fileserver.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsFileServerManagerStartup {

	@Inject
	public GenRightsFileServerManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(FileServerManagerAdminViewSecurityTarget.class);
	}
}
