package net.datenwerke.rs.installation;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PkgListSubCommand implements SubCommand {

   private static final String BASE_COMMAND = "list";

   private Provider<PackagedScriptHelper> packageScriptHelper;

   @Inject
   public PkgListSubCommand(Provider<PackagedScriptHelper> packageScriptHelper) {
      this.packageScriptHelper = packageScriptHelper;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      final CommandResult cr = new CommandResult();
      packageScriptHelper.get().listPackages().stream().map(File::getName).forEach(n -> cr.addResultLine());

      return cr;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {

   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

}
