package net.datenwerke.rs.terminal.service.terminal.basecommands;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.SslHelperService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class SsltestCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "ssltest";
   private final Provider<SslHelperService> sslHelperServiceProvider;

   @Inject
   public SsltestCommand(
         Provider<SslHelperService> sslHelperServiceProvider
         ) {
      this.sslHelperServiceProvider = sslHelperServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(
         messageClass = TerminalMessages.class, 
         name = BASE_COMMAND, 
         description = "commandSsltest_description",
         nonOptArgs = {
               @NonOptArgument(
                     name = "host", 
                     description = "commandSsltest_host",
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "port", 
                     description = "commandSsltest_port",
                     mandatory = true
                     )
               }
         )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (2 != arguments.size())
         return new CommandResult("2 arguments needed");
      
      String host = arguments.get(0);
      int port = Integer.parseInt(arguments.get(1));
      
      try {
         boolean ok = sslHelperServiceProvider.get().sslTest(host, port);
   
         if (ok) 
            return new CommandResult("Successfully connected to '" + host + ":" + port + "'");
         else
            return new CommandResult("Connection not possible to '" + host + ":" + port + "'");
      } catch (Exception e) {
         throw new IllegalStateException(ExceptionUtils.getRootCauseMessage(e), e);
      }

   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
