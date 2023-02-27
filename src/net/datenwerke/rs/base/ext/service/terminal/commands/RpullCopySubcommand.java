package net.datenwerke.rs.base.ext.service.terminal.commands;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterService;
import net.datenwerke.rs.base.ext.service.locale.RsBaseExtMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class RpullCopySubcommand implements RpullSubCommandHook {
   
   public static final String BASE_COMMAND = "copy";
   
   private final Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider;
   
   @Inject
   public RpullCopySubcommand(
         Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider
         ) {
      this.remoteEntityImporterServiceProvider = remoteEntityImporterServiceProvider;
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
         messageClass = RsBaseExtMessages.class, 
         name = BASE_COMMAND, 
         description = "commandRpullCopy_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "restUrl", 
                     description = "commandRpullCopy_restUrl",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "user", 
                     description = "commandRpullCopy_user",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "apikey", 
                     description = "commandRpullCopy_apikey",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "remoteEntityPath", 
                     description = "commandRpullCopy_remoteEntityPath",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "localTarget", 
                     description = "commandRpullCopy_localTarget",
                     mandatory = true
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (5 != arguments.size())
         throw new IllegalArgumentException("Exactly five arguments expected");
      
      try {
         ImportResult result = remoteEntityImporterServiceProvider.get().importRemoteEntities(arguments.get(0), arguments.get(1),
               arguments.get(2), arguments.get(3), arguments.get(4));
         return new CommandResult("Successful import. Results: '" + result + "'");
      } catch (Exception e) {
         throw new TerminalException(ExceptionUtils.getRootCauseMessage(e));
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
