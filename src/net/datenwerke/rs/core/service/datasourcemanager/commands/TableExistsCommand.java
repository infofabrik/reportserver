package net.datenwerke.rs.core.service.datasourcemanager.commands;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
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

public class TableExistsCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "tableExists";

   private final Provider<DatasourceHelperService> datasourceServiceProvider;

   @Inject
   public TableExistsCommand(
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
         description = "commandTableExists_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "datasource", 
                     description = "commandTableExists_datasource", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "table", 
                     description = "commandTableExists_table", 
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
         return new CommandResult(datasourceServiceProvider.get().tableExists(datasource, table)+"");
      } catch (Exception e) {
         throw new TerminalException(e);
      }
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
