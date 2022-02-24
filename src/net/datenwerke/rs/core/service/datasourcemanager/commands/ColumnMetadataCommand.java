package net.datenwerke.rs.core.service.datasourcemanager.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceHelperService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Read;

public class ColumnMetadataCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "columnMetadata";

   private final Provider<DatasourceHelperService> datasourceServiceProvider;

   @Inject
   public ColumnMetadataCommand(
         Provider<DatasourceHelperService> datasourceServiceProvider
         ) {
      this.datasourceServiceProvider = datasourceServiceProvider;
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
                     )
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<?> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() < 2)
         throw new IllegalArgumentException("at least 2 arguments required");
         
      String datasourceQuery = (String) nonOptionArguments.get(0);
      String table = (String) nonOptionArguments.get(1);
      
      try {
         DatabaseDatasource datasource = getDatasource(datasourceQuery, session);
         List<Map<String, Object>> metadata = datasourceServiceProvider.get().getColumnMetadata(datasource, table);
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
   
   private DatabaseDatasource getDatasource(String query, TerminalSession session) throws ObjectResolverException {
      Collection<Object> resolvedDatasource = session.getObjectResolver().getObjects(query, Read.class);
      if (1 != resolvedDatasource.size())
         throw new IllegalArgumentException("datasource must be resolved to exactly one object: \"" + query + "\"");
      Object asObject = resolvedDatasource.iterator().next();
      if (!(asObject instanceof DatabaseDatasource))
         throw new IllegalArgumentException("not a DatabaseDatasource: \"" + query + "\"");
      return (DatabaseDatasource) asObject;
   }

}
