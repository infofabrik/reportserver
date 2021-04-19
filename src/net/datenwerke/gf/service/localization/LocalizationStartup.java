package net.datenwerke.gf.service.localization;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.localization.terminal.LocalizationCommand;
import net.datenwerke.gf.service.localization.terminal.LocalizationImportMessagesSubCommand;
import net.datenwerke.gf.service.localization.terminal.hooks.LocalizationSubCommandHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class LocalizationStartup {

	@Inject
	public LocalizationStartup(HookHandlerService hookHandler,
			Provider<LocalizationCommand> localizationCommand,
			Provider<LocalizationImportMessagesSubCommand> localizationImportMsgsCommand
			){
		
		
		hookHandler.attachHooker(TerminalCommandHook.class, localizationCommand);
		hookHandler.attachHooker(LocalizationSubCommandHook.class, localizationImportMsgsCommand);
	}
}
