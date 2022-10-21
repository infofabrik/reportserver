package net.datenwerke.rs.incubator.service.aliascmd;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.incubator.service.aliascmd.terminal.commands.AliasCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class AliasCmdStartup {

   @Inject
   public AliasCmdStartup(HookHandlerService hookHandler, final AliasCommand aliasCommand) {

      hookHandler.attachHooker(TerminalCommandHook.class, aliasCommand, HookHandlerService.PRIORITY_LOWER);
      hookHandler.attachHooker(ReloadConfigNotificationHook.class, aliasCommand, HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(LateInitHook.class, () -> aliasCommand.reloadConfig(),
            HookHandlerService.PRIORITY_LOWER);
   }
}
