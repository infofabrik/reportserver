package net.datenwerke.rs.eximport.client.eximport;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.security.ExportSecurityTargetDomainHooker;
import net.datenwerke.rs.eximport.client.eximport.security.ImportSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class ExImportStartup {

	@Inject
	public ExImportStartup(
		HookHandlerService hookHandler,
			
		final ExportSecurityTargetDomainHooker exportSecurityTargetDomain,
		final ImportSecurityTargetDomainHooker importSecurityTargetDomain
		){
		
		/* attach to load rights */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(exportSecurityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, exportSecurityTargetDomain);

		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(importSecurityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, importSecurityTargetDomain);

	}
}
