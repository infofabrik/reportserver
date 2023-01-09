package net.datenwerke.rs.terminal.service.terminal.basecommands.infocommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
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
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;

public class InfoDatasourceSubcommand implements InfoSubcommandHook{
public static final String BASE_COMMAND = "datasource";
   
   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   
   private final Provider<TerminalService> terminalServiceProvider;
   
   private Map<String, String> generalInfo;
   private Map<String, String> urlInfo;
   private Map<String, String> functionsSection;
   private Map<String, String> supportsSection;
   
   @Inject
   public InfoDatasourceSubcommand(
         Provider<DatasourceHelperService> datasourceServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.datasourceServiceProvider = datasourceServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      initMaps(datasourceServiceProvider.get().getDatasourceInfoDefinition());
   }


   private void initMaps(Map<String, Object> datasourceInfoDefinition) {
      generalInfo = (Map<String, String>) datasourceInfoDefinition.get("generalInfo");
      urlInfo = (Map<String, String>) datasourceInfoDefinition.get("urlInfo");
      functionsSection = (Map<String, String>) datasourceInfoDefinition.get("functionsSection");
      supportsSection = (Map<String, String>) datasourceInfoDefinition.get("supportsSection");
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
         messageClass = TerminalMessages.class, 
         name = BASE_COMMAND, 
         description = "commandInfoDatasource_desc",
         nonOptArgs = {
               @NonOptArgument(
                  name = "datasource", 
                  description = "commandInfoDatasource_datasource",
                  mandatory = true
               )
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() != 1)
         throw new IllegalArgumentException("exactly 1 argument required");
      String datasourceQuery = (String) nonOptionArguments.get(0);
      
      
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         Map<String, Object> results = datasourceServiceProvider.get().fetchInfoDatasourceMetadata(datasource);
         return generateCommandResult(results);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }


   private CommandResult generateCommandResult(Map<String, Object> results)  {
      ArrayList<RSTableModel> resultTables = new ArrayList<RSTableModel>();
      resultTables
            .add(generateRsTableModel(generalInfo, "General information", results, Optional.of(Arrays.asList(100, 0))));
      resultTables.add(generateRsTableModel(urlInfo, "URL information", results, Optional.of(Arrays.asList(100, 0))));
      resultTables.add(
            generateRsTableModel(functionsSection, "Functions section", results, Optional.of(Arrays.asList(100, 0))));
      resultTables.add(generateRsTableModel(supportsSection, "Supports section", results, Optional.empty()));
      
      CommandResult commandResult = new CommandResult();
      resultTables.forEach(table -> commandResult.addResultTable(table));
      return commandResult;
   }
   
   RSTableModel generateRsTableModel(Map<String, String> tableMeta, String header, Map<String, Object> results,
         Optional<List<Integer>> colWidths) {
      RSTableModel tableModel = new RSTableModel();
      TableDefinition tableDefinition = new TableDefinition(Arrays.asList(header, ""),
            Arrays.asList(String.class, String.class));
      if (colWidths.isPresent())
         tableDefinition.setDisplaySizes(colWidths.get());
      
      tableModel.setTableDefinition(tableDefinition);
      List<String> keyList = new ArrayList<String>((tableMeta.keySet()));
      Collections.sort(keyList);
      keyList.forEach(key -> {
         Object res = results.get(tableMeta.get(key));
         tableModel.addDataRow(new RSStringTableRow(key, null == res? "null": res.toString()));
      });
      return tableModel;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
