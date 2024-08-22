package net.datenwerke.gf.service.properties.terminal.commands;

import static net.datenwerke.gf.service.properties.entities.Property.isInternalKey;

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
import net.datenwerke.rs.utils.string.Emoji;

public class PropertiesRemoveSubCommand implements PropertiesSubCommandHook {

   public static final String BASE_COMMAND = "remove";

   private final Provider<PropertiesService> propertiesServiceProvider;

   @Inject
   public PropertiesRemoveSubCommand(
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
         description = "commandProperties_sub_remove_description",
         nonOptArgs = {
               @NonOptArgument(
                     name = "key", 
                     description = "commandPropertiesRemove_key",
                     mandatory = true
               )
         }
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 != arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Exactly one argument expected");
      
      String key = arguments.get(0);
      final PropertiesService propertiesService = propertiesServiceProvider.get();
      if (isInternalKey(key) || !propertiesService.containsKey(key))
         throw new TerminalException(Emoji.exceptionEmoji().getEmoji(" ") + "Key '" + key + "' does not exist");
         
      propertiesService.removeByKey(key);
      return new CommandResult(Emoji.BEER_MUG.getEmoji(" ") + "Key '" + key + "' removed successfully");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
