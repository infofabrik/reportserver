package net.datenwerke.gf.service.properties.terminal.commands;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toMap;

import java.util.TreeMap;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.properties.PropertiesService;
import net.datenwerke.gf.service.properties.entities.Property;
import net.datenwerke.gf.service.properties.locale.PropertiesMessages;
import net.datenwerke.gf.service.properties.terminal.hooks.PropertiesSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PropertiesListSubCommand implements PropertiesSubCommandHook {

   public static final String BASE_COMMAND = "list";

   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<PropertiesService> propertiesServiceProvider;

   @Inject
   public PropertiesListSubCommand(
         Provider<TerminalService> terminalServiceProvider,
         Provider<PropertiesService> propertiesServiceProvider
         ) {
      this.terminalServiceProvider = terminalServiceProvider;
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
         description = "commandProperties_sub_list_description"
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return terminalServiceProvider.get().convertSimpleMapToCommandResult(emptyList(),
            propertiesServiceProvider.get().getAllProperties()
               .stream()
               .filter(Property::isExternal)
               .collect(toMap(Property::getKey, Property::getValue, (o1, o2) -> o1, TreeMap::new)));
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
