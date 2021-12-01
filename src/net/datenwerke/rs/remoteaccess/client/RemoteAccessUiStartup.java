package net.datenwerke.rs.remoteaccess.client;

import javax.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteaccess.client.sftp.genrights.SftpSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class RemoteAccessUiStartup {
	
	@Inject
	public RemoteAccessUiStartup(
			HookHandlerService hookHandler, 
			SftpSecurityTargetDomainHooker securityTargetDomain
			) {
		
		/* attach security target domains */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
	}

}
