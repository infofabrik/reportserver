package net.datenwerke.rs.base.service.datasources.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.google.inject.Inject;
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

public class DatasourceMetadataCommand implements TerminalCommandHook{
   
   public static final String BASE_COMMAND = "datasourceMetadata";
   
   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public DatasourceMetadataCommand(
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
         description = "commandDatasourceMetadata_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "datasource", 
                     description = "commandDatasourceMetadata_datasource", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "methodName", 
                     description = "commandDatasourceMetadata_methodName", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "arg", 
                     description = "commandDatasourceMetadata_methodArg", 
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
      String methodName = (String) nonOptionArguments.get(1);
      List<String> args;
      if (nonOptionArguments.size() == 2) {
         args = new ArrayList<String>();
      } else {
         args = IntStream.range(2, nonOptionArguments.size())
               .mapToObj(i -> nonOptionArguments.get(i))
               .collect(toList());
      }
      
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         Object result = datasourceServiceProvider.get().fetchDatasourceMetadata(datasource, methodName, args);
         return generateCommandResult(result);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }


   private CommandResult generateCommandResult(Object result) throws TerminalException {
      if (result instanceof ResultSet) {
         ResultSet rs = (ResultSet) result;
         try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> columnNames= new ArrayList<>();
            List<Class<?>> types= new ArrayList<>();
            for(int i = 0; i < columnCount; i++) {
               columnNames.add(metaData.getColumnName(i+1));
               types.add(String.class);
            }
            TableDefinition td = new TableDefinition(columnNames,types);
            RSTableModel table = new RSTableModel(td);
            while (rs.next()) {
               List<String> values = new ArrayList<>();
               for (int i = 0; i < columnCount; i++) {
                  try {
                     values.add(rs.getObject(i + 1).toString());
                  } catch (Exception e) {
                     values.add("null");
                  }
               }
               table.addDataRow(new RSStringTableRow(values));
            }
            return new CommandResult(table);
         } catch (SQLException e) {
            throw new TerminalException(e);
         }
      } else {
         return new CommandResult(result.toString());
      }
   }


   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
}
