package net.datenwerke.rs.terminal.service.terminal.hooks;

import static java.util.stream.Collectors.toList;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;

public abstract class SubSubCommandContainerImpl extends SubCommandContainerImpl {

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.addAutocompleteNamesForToken(3, 
         getSubCommands()
         .stream()
         .map(SubCommand::getBaseCommand)
         .collect(toList())
         );
   }
}
