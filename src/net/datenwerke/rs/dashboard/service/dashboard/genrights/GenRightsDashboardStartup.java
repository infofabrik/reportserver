package net.datenwerke.rs.dashboard.service.dashboard.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsDashboardStartup {

	@Inject
	public GenRightsDashboardStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(DashboardViewSecurityTarget.class);
		securityService.registerSecurityTarget(DashboardAdminSecurityTarget.class);
	}
}
