package net.datenwerke.rs.eximport.service.eximport.terminal.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.locale.ExImportMessages;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ExportSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

public class ExportAllCommand implements ExportSubCommandHook {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   public static final String BASE_COMMAND = "all";

   private final ExportService exportService;
   private final HookHandlerService hookHandler;

   @Inject
   public ExportAllCommand(ExportService exportService, HookHandlerService hookHandler) {

      /* store objects */
      this.exportService = exportService;
      this.hookHandler = hookHandler;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = ExImportMessages.class, name = BASE_COMMAND, description = "commandExport_sub_all_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      final ExportConfig config = new ExportConfig();

      hookHandler.getHookers(ExportAllHook.class).forEach(exporter -> {
         logger.info("configure exporter: " + exporter.getClass());
         exporter.configure(config);
      });

      CommandResult result = new CommandResult(exportService.exportIndent(config));
      result.setDisplayMode(DisplayMode.WINDOW);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
