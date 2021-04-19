package net.datenwerke.rs.core.service.genrights.reportmanager;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsReportManagerStartup {

	@Inject
	public GenRightsReportManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(ReportManagerAdminViewSecurityTarget.class);
	}
}
