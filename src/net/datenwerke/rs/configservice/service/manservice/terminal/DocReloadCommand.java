package net.datenwerke.rs.configservice.service.manservice.terminal;

import com.google.inject.Inject;

import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.configservice.service.manservice.locale.ManMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.man.ManPageService;

public class DocReloadCommand implements DocSubCommandHook {

   public static final String BASE_COMMAND = "reload";

   private final ManPageService manPageService;

   @Inject
   public DocReloadCommand(ManPageService manPageService) {
      this.manPageService = manPageService;
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
   @CliHelpMessage(messageClass = ManMessages.class, name = BASE_COMMAND, description = "commandDoc_sub_reload_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      manPageService.clearCache();

      return new CommandResult(ConfigMessages.INSTANCE.configReloaded());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
