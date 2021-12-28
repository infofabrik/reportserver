package net.datenwerke.rs.adminutils.client.logs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.logs.terminal.commandproc.ViewLogFileTerminalCommandProcessor;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class LogFilesUiStartup {

   @Inject
   public LogFilesUiStartup(final HookHandlerService hookHandler,

         Provider<ViewLogFileTerminalCommandProcessor> commandProcessor) {

      /* terminal commands */
      hookHandler.attachHooker(CommandResultProcessorHook.class, commandProcessor);

   }
}
