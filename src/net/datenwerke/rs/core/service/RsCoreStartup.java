package net.datenwerke.rs.core.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.core.service.login.BlockSuperUserOnLoginHooker;
import net.datenwerke.rs.core.service.login.CheckBlockedUserOnLoginHooker;
import net.datenwerke.rs.core.service.login.UserDtoSetStatusPostProcessor;
import net.datenwerke.rs.core.service.maintenance.BlockUserMaintenanceTask;
import net.datenwerke.rs.core.service.pdf.RegisterPdfFontsOnConfigReload;
import net.datenwerke.rs.core.service.pdf.RegisterPdfFontsOnStartup;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.hooks.UserDtoPostProcessorHook;

public class RsCoreStartup {

	@Inject
	public RsCoreStartup(
		HookHandlerService hookHandler,
		
		Provider<UserDtoSetStatusPostProcessor> userSetStatusDtoPostProcessor,
		
		Provider<CheckBlockedUserOnLoginHooker> checkBlockUserOnLogin,
		Provider<BlockSuperUserOnLoginHooker> blockRootOnLogin,
		
		Provider<BlockUserMaintenanceTask> blockTask,
		
		RegisterPdfFontsOnConfigReload pdfFontOnReloadLoader,
		RegisterPdfFontsOnStartup pdfFontOnStartupLoader,
		Provider<EnvironmentAfterStartupInformation> afterStartupInfoProvider
		){
		
		
		hookHandler.attachHooker(UserDtoPostProcessorHook.class, userSetStatusDtoPostProcessor);
		
		hookHandler.attachHooker(PostAuthenticateHook.class, checkBlockUserOnLogin);
		
		hookHandler.attachHooker(PostAuthenticateHook.class, blockRootOnLogin);
		
		hookHandler.attachHooker(MaintenanceTask.class, blockTask);
		
		hookHandler.attachHooker(ReloadConfigNotificationHook.class, pdfFontOnReloadLoader);
		hookHandler.attachHooker(LateInitHook.class, pdfFontOnStartupLoader);
		
		hookHandler.attachHooker(LateInitHook.class, afterStartupInfoProvider);
	}
}
