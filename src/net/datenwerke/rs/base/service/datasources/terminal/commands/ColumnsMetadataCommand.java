package net.datenwerke.rs.base.service.datasources.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

public class ColumnsMetadataCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "columnsMetadata";

   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;

   @Inject
   public ColumnsMetadataCommand(
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
         description = "commandColumnMetadata_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "datasource", 
                     description = "commandColumnMetadata_datasource", 
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "table", 
                     description = "commandColumnMetadata_table", 
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "column", 
                     description = "commandColumnMetadata_columns", 
                     varArgs = true
               )
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() < 2)
         throw new IllegalArgumentException("at least 2 arguments required");
         
      String datasourceQuery = (String) nonOptionArguments.get(0);
      String table = (String) nonOptionArguments.get(1);
      
      List<String> cols = 
            IntStream.range(2, nonOptionArguments.size())
            .mapToObj(i -> nonOptionArguments.get(i))
            .collect(toList());
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         List<Map<String, Object>> metadata = datasourceServiceProvider.get().getColumnMetadata(datasource, table, cols);
         return createResult(metadata);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }

   private CommandResult createResult(List<Map<String, Object>> metadata) {
      if (0 == metadata.size())
         return new CommandResult("no columns found");
      
      Map<String,Object> firstColumn = metadata.get(0);
      List<String> headers = new ArrayList<>();
      List<Class<?>> types = new ArrayList<>();
      firstColumn.keySet().forEach(headers::add);
      
      CommandResult result = new CommandResult();
      RSTableModel metadataTable = new RSTableModel();
      TableDefinition metaDef = new TableDefinition(headers, types);
      metadataTable.setTableDefinition(metaDef);
      result.addResultTable(metadataTable);
      metadata.forEach(columnMetadata -> {
         List<String> vals = new ArrayList<>();
         IntStream.range(0, columnMetadata.size()).forEach(i -> {
            String header = headers.get(i);
            Object val = columnMetadata.get(header);
            vals.add(null == val? null+"": val.toString());
         });
         metadataTable.addDataRow(new RSStringTableRow(vals));
      });
      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
}
