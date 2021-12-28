package net.datenwerke.rs.terminal.service.terminal.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsTerminalManagerStartup {

	@Inject
	public GenRightsTerminalManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(TerminalSecurityTarget.class);
	}
}
