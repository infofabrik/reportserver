package net.datenwerke.rs.adminutils.service.logs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.ListLogFilesCommand;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class LogFilesStartup {

   @Inject
   public LogFilesStartup(HookHandlerService hookHandler,

         Provider<ListLogFilesCommand> listLogFilesCommandProvider,
         Provider<ViewLogFileCommand> viewLogFileCommandProvider) {

      hookHandler.attachHooker(TerminalCommandHook.class, listLogFilesCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, viewLogFileCommandProvider);
   }
}
