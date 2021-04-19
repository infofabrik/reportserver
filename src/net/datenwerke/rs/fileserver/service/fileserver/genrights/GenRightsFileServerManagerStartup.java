package net.datenwerke.rs.fileserver.service.fileserver.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsFileServerManagerStartup {

	@Inject
	public GenRightsFileServerManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(FileServerManagerAdminViewSecurityTarget.class);
	}
}
