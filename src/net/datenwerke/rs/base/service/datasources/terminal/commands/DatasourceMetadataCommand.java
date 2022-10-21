package net.datenwerke.rs.base.service.datasources.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
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
      
      Map<String, List<String>> methodDescriptions = new HashMap<>();
      methodDescriptions.put(methodName, args);
      
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         Map<String, Object> results = datasourceServiceProvider.get().fetchDatasourceMetadata(datasource, methodDescriptions);
         return generateCommandResult(results.get(methodName));
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }


   private CommandResult generateCommandResult(Object result) throws TerminalException {
      if (result instanceof ResultSet) {
         ResultSet rs = (ResultSet) result;
         try {
            return terminalServiceProvider.get().convertResultSetToCommandResult(rs);
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
