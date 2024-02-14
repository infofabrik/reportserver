package net.datenwerke.rs.transport.service.transport.terminal.commands;

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
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class TransportCloseSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "close";
   
   private final Provider<TransportService> transportServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public TransportCloseSubcommand(
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
         description = "commandTransportClose_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "transport", 
                     description = "commandTransportClose",
                     mandatory = true
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 != arguments.size()) {
	  throw new IllegalArgumentException("Exactly one argument expected");
      }
      
      Transport transport = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(Transport.class,
            arguments.get(0), session, Read.class, Write.class);
      if (transport.isClosed()) {
          throw new IllegalArgumentException("Cannot close a closed transport");
      }
      
      transportServiceProvider.get().close(transport);
      return new CommandResult("Transport '" + transport + "' was successfully closed");
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
