package net.datenwerke.rs.scheduler.service.scheduler.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsSchedulingStartup {

	@Inject
	public GenRightsSchedulingStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(SchedulingAdminSecurityTarget.class);
		securityService.registerSecurityTarget(SchedulingBasicSecurityTarget.class);
	}
}
