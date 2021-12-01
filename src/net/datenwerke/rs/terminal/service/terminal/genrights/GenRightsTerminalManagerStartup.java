package net.datenwerke.rs.terminal.service.terminal.genrights;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.Inject;

public class GenRightsTerminalManagerStartup {

	@Inject
	public GenRightsTerminalManagerStartup(
		SecurityService securityService	
		){
		
		/* register security targets */
		securityService.registerSecurityTarget(TerminalSecurityTarget.class);
	}
}
