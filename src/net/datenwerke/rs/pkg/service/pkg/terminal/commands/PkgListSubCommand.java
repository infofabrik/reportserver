package net.datenwerke.rs.pkg.service.pkg.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.pkg.service.pkg.PackagedScriptHelperService;
import net.datenwerke.rs.pkg.service.pkg.locale.PkgMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PkgListSubCommand implements SubCommand {

   private static final String BASE_COMMAND = "list";

   private Provider<PackagedScriptHelperService> packageScriptHelperProvider;
   private Provider<TerminalService> terminalServiceProvider;

   @Inject
   public PkgListSubCommand(
         Provider<PackagedScriptHelperService> packageScriptHelperProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.packageScriptHelperProvider = packageScriptHelperProvider;
      this.terminalServiceProvider = terminalServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(
         messageClass = PkgMessages.class, 
         name = BASE_COMMAND, 
         description = "pkg_sub_list_description"
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return terminalServiceProvider.get().convertSimpleListToCommandResult("Available packages",
         packageScriptHelperProvider.get().listPackages()
            .stream()
            .map(File::getName)
            .sorted()
            .collect(toList()));
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {

   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

}
