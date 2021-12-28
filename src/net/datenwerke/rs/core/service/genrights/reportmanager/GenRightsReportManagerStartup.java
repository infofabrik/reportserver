package net.datenwerke.rs.core.service.genrights.reportmanager;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsReportManagerStartup {

	@Inject
	public GenRightsReportManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(ReportManagerAdminViewSecurityTarget.class);
	}
}
