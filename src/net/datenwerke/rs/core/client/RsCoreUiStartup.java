package net.datenwerke.rs.core.client;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.genrights.AccessRsSecurityTargetDomainHooker;
import net.datenwerke.rs.core.client.objectinfo.BasicObjectInfo;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RsCoreUiStartup {

	@Inject
	public RsCoreUiStartup(
		HookHandlerService hookHandler,
		
		AccessRsSecurityTargetDomainHooker securityTargetDomain,
		
		final Provider<BasicObjectInfo> baseObjectInfo
		
		){
		
		/* attach security target domains */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
		
		hookHandler.attachHooker(ObjectPreviewTabProviderHook.class, baseObjectInfo, HookHandlerService.PRIORITY_LOWER);
	}
}
