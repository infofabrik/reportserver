package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.transport.client.transport.dto.TransportElementDto;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class TransportRemoveSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "remove";
   
   private final Provider<TransportService> transportServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public TransportRemoveSubcommand(
         Provider<TransportService> transportServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }
   
   @CliHelpMessage(
         messageClass = TransportManagerMessages.class, 
         name = BASE_COMMAND, 
         description = "commandTransportRemove_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "transport", 
                     description = "commandTransportRemove_transport",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "type", 
                     description = "commandTransportRemove_type",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "key", 
                     description = "commandTransportRemove_key",
                     mandatory = true
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (3 != arguments.size())
         throw new IllegalArgumentException("Exactly three arguments expected");
      Transport transport = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(Transport.class,
            arguments.get(0), session, Read.class, Write.class);

      TransportElementDto elementToRemove = new TransportElementDto(arguments.get(1), arguments.get(2));
      List<TransportElementDto> toRemove = Collections.singletonList(elementToRemove);
      transportServiceProvider.get().removeElements(transport, toRemove);

      return new CommandResult("Removed from transport '" + transport + "': '" + elementToRemove.getKey() + "'");
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
