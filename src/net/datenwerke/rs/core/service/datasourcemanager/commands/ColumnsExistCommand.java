package net.datenwerke.rs.core.service.datasourcemanager.commands;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceHelperService;
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

public class ColumnsExistCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "columnsExist";

   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;

   @Inject
   public ColumnsExistCommand(
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
         description = "commandColumnsExist_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "datasource", 
                     description = "commandColumnsExist_datasource", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "table", 
                     description = "commandColumnsExist_table", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "columns", 
                     description = "commandColumnsExist_columns", 
                     mandatory = true
                     )
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() < 3)
         throw new IllegalArgumentException("at least 3 arguments required");
         
      String datasourceQuery = (String) nonOptionArguments.get(0);
      String table = (String) nonOptionArguments.get(1);
      List<String> cols = 
         IntStream.range(2, nonOptionArguments.size())
         .mapToObj(i -> nonOptionArguments.get(i))
         .collect(toList());
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         List<String> nonExisting = datasourceServiceProvider.get().getNonExistingColumns(datasource, table, cols);
         if (nonExisting.isEmpty())
            return new CommandResult(true+"");
         else {
            CommandResult result = new CommandResult(false+"");
            RSTableModel nonExistingTable = new RSTableModel();
            TableDefinition nonExistingDef = new TableDefinition(Arrays.asList("Non-existing columns"),
                  Arrays.asList(String.class));
            nonExistingTable.setTableDefinition(nonExistingDef);
            nonExisting.forEach(col -> nonExistingTable.addDataRow(new RSStringTableRow(col)));
            result.addResultTable(nonExistingTable);
            return result;
         }
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
}
