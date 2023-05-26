package net.datenwerke.rs.installation;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class RsInstallStartup {

   @Inject
   public RsInstallStartup(HookHandlerService hookHandler,
         final Provider<ReportServerInstallationServiceImpl> installer, Provider<PkgCommand> isntPkgCommandProvider) {

      hookHandler.attachHooker(TerminalCommandHook.class, isntPkgCommandProvider);

      hookHandler.attachHooker(LateInitHook.class, () -> installer.get().install(),
            HookHandlerService.PRIORITY_HIGH - 10 /* HIGHER */);
   }
}
