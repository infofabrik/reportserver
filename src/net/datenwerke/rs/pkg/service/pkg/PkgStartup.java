package net.datenwerke.rs.pkg.service.pkg;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.pkg.service.pkg.hooks.PkgSubCommandHook;
import net.datenwerke.rs.pkg.service.pkg.terminal.commands.PkgCommand;
import net.datenwerke.rs.pkg.service.pkg.terminal.commands.PkgInstallSubCommand;
import net.datenwerke.rs.pkg.service.pkg.terminal.commands.PkgListSubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class PkgStartup {

   @Inject
   public PkgStartup(
         HookHandlerService hookHandler,
         Provider<PkgCommand> pkgCommandProvider,
         Provider<PkgInstallSubCommand> pkgSubInstallProvider,
         Provider<PkgListSubCommand> pkgSubListProvider
         ) {

      hookHandler.attachHooker(TerminalCommandHook.class, pkgCommandProvider);
      hookHandler.attachHooker(PkgSubCommandHook.class, pkgSubInstallProvider);
      hookHandler.attachHooker(PkgSubCommandHook.class, pkgSubListProvider);
   }
}
