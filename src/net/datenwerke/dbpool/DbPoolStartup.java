package net.datenwerke.dbpool;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dbpool.terminal.commands.ConnPoolStatsCommand;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class DbPoolStartup {

   @Inject
   public DbPoolStartup(HookHandlerService hookHandler,

         Provider<ConnPoolStatsCommand> connPoolStatsCommandProvider) {

      hookHandler.attachHooker(TerminalCommandHook.class, connPoolStatsCommandProvider);
   }
}
