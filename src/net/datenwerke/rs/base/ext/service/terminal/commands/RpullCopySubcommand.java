package net.datenwerke.rs.base.ext.service.terminal.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterService;
import net.datenwerke.rs.base.ext.service.locale.RsBaseExtMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.misc.DateUtils;

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
         args = {
               @Argument(
                     flag = "v", 
                     hasValue = false, 
                     valueName = "sort", 
                     description = "commandRpullCopy_flagV", 
                     mandatory = false
               )
         },
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
      
      final String argStr = "v";
      final boolean includeVariants = parser.hasOption("v", argStr);
      
      try {
         Instant start = Instant.now();
         ImportResult result = remoteEntityImporterServiceProvider.get().importRemoteEntities(arguments.get(0), arguments.get(1),
               arguments.get(2), arguments.get(3), arguments.get(4), includeVariants);
         Instant end = Instant.now();
         CommandResult commandResult = new CommandResult();
         
         final RSTableModel resultsTable = new RSTableModel();
         final TableDefinition resultsTableDefinition = new TableDefinition(Arrays.asList("Results", ""),
               Arrays.asList(String.class, String.class));
         resultsTable.setTableDefinition(resultsTableDefinition);
         resultsTable.addDataRow(new RSStringTableRow("Status", "OK"));
         resultsTable.addDataRow(new RSStringTableRow("Duration", Duration.between(start, end).toString()));
         resultsTable.addDataRow(new RSStringTableRow("Imported objects", result.getImportedObjects().size() + ""));
         resultsTable.addDataRow(new RSStringTableRow("Import date", DateUtils.format(result.getDate())));
         resultsTable.addDataRow(new RSStringTableRow("Name", result.getName()));
         commandResult.addResultTable(resultsTable);
         
         final RSTableModel detailsTable = new RSTableModel();
         final TableDefinition detailsTableDefinition = new TableDefinition(
               Arrays.asList("Imported objects (" + result.getImportedObjects().size() + ")"),
               Arrays.asList(String.class));
         detailsTable.setTableDefinition(detailsTableDefinition);
         result.getImportedObjects().entrySet()
               .forEach(entry -> detailsTable.addDataRow(new RSStringTableRow(entry.getValue().toString())));
         commandResult.addResultTable(detailsTable);
         
         return commandResult;
      } catch (Exception e) {
         throw new TerminalException(ExceptionUtils.getRootCauseMessage(e));
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
