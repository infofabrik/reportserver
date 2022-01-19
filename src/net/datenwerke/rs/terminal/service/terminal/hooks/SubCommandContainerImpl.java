package net.datenwerke.rs.terminal.service.terminal.hooks;

import static java.util.stream.Collectors.toList;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public abstract class SubCommandContainerImpl implements SubCommandContainer, TerminalCommandHook {

   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      TerminalCommandHook sub = getSubCommand(parser, session);
      if (null != sub)
         return sub.execute(parser.getSubCommandParser(), session);

      return handleDefault(parser, session);
   }

   protected CommandResult handleDefault(CommandParser parser, TerminalSession session) {
      return new CommandResult("Could not find subcommand");
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return getBaseCommand().equals(parser.getBaseCommand());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      if (!consumes(autocompleteHelper.getParser(), session))
         autocompleteHelper.autocompleteBaseCommand(getBaseCommand());
      else {
         TerminalCommandHook subCommand = getSubCommand(autocompleteHelper.getParser(), session);
         if (null != subCommand)
            subCommand.addAutoCompletEntries(autocompleteHelper, session);
         else {
            autocompleteHelper.addAutocompleteNamesForToken(2, 
                  getSubCommands()
                  .stream()
                  .map(SubCommand::getBaseCommand)
                  .collect(toList())
                  );
         }
      }
   }

   @Override
   public TerminalCommandHook getSubCommand(final CommandParser parser, final TerminalSession session) {
      String arg = parser.getArgumentNr(1);
      if (null == arg)
         return null;
      
      return getSubCommands()
         .stream()
         .filter(command -> command.consumes(parser.getSubCommandParser(), session))
         .findAny()
         .orElse(null);
   }

}
