package net.datenwerke.rs.core.service.genrights.datasources;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsDatasourceManagerStartup {

	@Inject
	public GenRightsDatasourceManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(DatasourceManagerAdminViewSecurityTarget.class);
	}
}
