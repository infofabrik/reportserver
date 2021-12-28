package net.datenwerke.rs.core.service.genrights.datasources;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsDatasourceManagerStartup {

	@Inject
	public GenRightsDatasourceManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(DatasourceManagerAdminViewSecurityTarget.class);
	}
}
