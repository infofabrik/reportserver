package net.datenwerke.rs.base.service.datasources.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;

public class CopyTableContentsCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "copyTableContents";

   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;

   @Inject
   public CopyTableContentsCommand(
         Provider<DatasourceHelperService> datasourceServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.datasourceServiceProvider = datasourceServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = DatasourcesMessages.class, 
         name = BASE_COMMAND, 
         description = "commandCopyTableContents_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "sourceDatasource", 
                     description = "commandCopyTableContents_sourceDatasource", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "sourceTable", 
                     description = "commandCopyTableContents_sourceTable", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "destinationDatasource", 
                     description = "commandCopyTableContents_destinationDatasource", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "destinationTable", 
                     description = "commandCopyTableContents_destinationTable", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "primaryKeys", 
                     description = "commandCopyTableContents_primaryKeys", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "copyPrimaryKeys", 
                     description = "commandCopyTableContents_copyPrimaryKeys", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "batchSize", 
                     description = "commandCopyTableContents_batchSize", 
                     mandatory = false
                     )
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<?> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() < 6)
         throw new IllegalArgumentException("at least 6 arguments required");
         
      String sourceDatasourceQuery = (String) nonOptionArguments.get(0);
      String sourceTable = (String) nonOptionArguments.get(1);
      String destinationDatasourceQuery = (String) nonOptionArguments.get(2);
      String destinationTable = (String) nonOptionArguments.get(3);
      String primaryKeys = (String) nonOptionArguments.get(4);
      boolean copyPrimaryKeys = Boolean.parseBoolean((String) nonOptionArguments.get(5));
      
      int batchSize = 100; //default
      if (7 == nonOptionArguments.size())
         batchSize = Integer.parseInt((String) nonOptionArguments.get(6));
      
      try {
         DatabaseDatasource sourceDatasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, sourceDatasourceQuery, session, Read.class);
         DatabaseDatasource destinationDatasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, destinationDatasourceQuery, session, Read.class);

         Map<String, Object> results = datasourceServiceProvider.get().copyTable(
               sourceDatasource, sourceTable,
               destinationDatasource, destinationTable, 
               Arrays.asList(
                     primaryKeys.trim().split("\\s*;\\s*"))
                     .stream()
                     .map(String::toUpperCase)
                     .collect(toList()),
               copyPrimaryKeys, 
               batchSize
               );
         return printResults(results);
         
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }

   private CommandResult printResults(Map<String, Object> results) {
      CommandResult result = new CommandResult();

      final RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("Results", ""), Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);
      
      table.addDataRow(new RSStringTableRow("Status", "OK"));

      results.forEach((key, val) -> table.addDataRow(new RSStringTableRow(key, val.toString())));

      result.addResultTable(table);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   

}
