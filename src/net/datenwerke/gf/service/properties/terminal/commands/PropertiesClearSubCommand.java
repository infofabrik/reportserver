package net.datenwerke.gf.service.properties.terminal.commands;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.properties.PropertiesService;
import net.datenwerke.gf.service.properties.entities.Property;
import net.datenwerke.gf.service.properties.locale.PropertiesMessages;
import net.datenwerke.rs.scripting.service.scripting.terminal.hooks.ScheduleScriptSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PropertiesClearSubCommand implements ScheduleScriptSubCommandHook {

   public static final String BASE_COMMAND = "clear";

   private final Provider<PropertiesService> propertiesServiceProvider;

   @Inject
   public PropertiesClearSubCommand(
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
         description = "commandProperties_sub_clear_description"
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      final PropertiesService propertiesService = propertiesServiceProvider.get();
      propertiesService.getAllProperties()
            .stream()
            .filter(Property::isExternal)
            .map(Property::getKey)
            .forEach(propertiesService::removeByKey);
      return new CommandResult("Properties cleared successfully");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
