package net.datenwerke.gf.service.properties.terminal.commands;

import static net.datenwerke.gf.service.properties.entities.Property.isExternalKey;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.properties.PropertiesService;
import net.datenwerke.gf.service.properties.locale.PropertiesMessages;
import net.datenwerke.gf.service.properties.terminal.hooks.PropertiesSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PropertiesContainsSubCommand implements PropertiesSubCommandHook {

   public static final String BASE_COMMAND = "contains";

   private final Provider<PropertiesService> propertiesServiceProvider;

   @Inject
   public PropertiesContainsSubCommand(
         Provider<PropertiesService> propertiesServiceProvider
         ) {
      this.propertiesServiceProvider = propertiesServiceProvider;
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
   @CliHelpMessage(
         messageClass = PropertiesMessages.class, 
         name = BASE_COMMAND, 
         description = "commandProperties_sub_contains_description",
         nonOptArgs = {
               @NonOptArgument(
                     name = "key", 
                     description = "commandPropertiesContains_key",
                     mandatory = true
               )
         }
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 != arguments.size())
         throw new IllegalArgumentException("Exactly one argument expected");
      
      String key = arguments.get(0);
      return new CommandResult((isExternalKey(key) && propertiesServiceProvider.get().containsKey(key))+"");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
