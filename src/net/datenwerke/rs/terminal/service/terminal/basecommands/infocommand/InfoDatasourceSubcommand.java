package net.datenwerke.rs.terminal.service.terminal.basecommands.infocommand;

import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE;
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE_FUNCTIONS;
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE_SUPPORTS;
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.JDBC_URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.datasources.DatasourceInfoType;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
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
   
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   private final Map<String, String> databaseInfo;
   private final Map<String, String> jdbcUrlInfo;
   private final Map<String, String> functionsSection;
   private final Map<String, String> supportsSection;
   
   @Inject
   public InfoDatasourceSubcommand(
         Provider<DatasourceHelperService> datasourceServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.datasourceHelperServiceProvider = datasourceServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      final Map<DatasourceInfoType, Object> datasourceInfoDefinition = datasourceServiceProvider.get()
            .getDatasourceInfoDefinition();
      databaseInfo = (Map<String, String>) datasourceInfoDefinition.get(DATABASE);
      jdbcUrlInfo = (Map<String, String>) datasourceInfoDefinition.get(JDBC_URL);
      functionsSection = (Map<String, String>) datasourceInfoDefinition.get(DATABASE_FUNCTIONS);
      supportsSection = (Map<String, String>) datasourceInfoDefinition.get(DATABASE_SUPPORTS);
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
         Map<String, Object> results = datasourceHelperServiceProvider.get().fetchInfoDatasourceMetadata(datasource,
               true, true, true, true);
         return generateCommandResult(results, datasource);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }


   private CommandResult generateCommandResult(Map<String, Object> results, DatasourceDefinition datasource) {
      List<RSTableModel> resultTables = new ArrayList<>();
      Map<String, Object> generalInformation = datasourceHelperServiceProvider.get().getGeneralInformation(datasource);
      resultTables.add(generateGeneralInformationModel("General information", generalInformation));
      resultTables
            .add(generateTableModel(databaseInfo, "Database information", results, Optional.of(Arrays.asList(150, 0))));
      resultTables
            .add(generateTableModel(jdbcUrlInfo, "JDBC URL information", results, Optional.of(Arrays.asList(150, 0))));
      resultTables.add(generateTableModel(functionsSection, "Database functions section", results,
            Optional.of(Arrays.asList(150, 0))));
      resultTables.add(generateTableModel(supportsSection, "Database supports section", results, Optional.empty()));

      CommandResult commandResult = new CommandResult();
      resultTables.forEach(table -> commandResult.addResultTable(table));
      return commandResult;
   }
   
   private RSTableModel generateGeneralInformationModel(String header, Map<String,Object> results) {
      RSTableModel tableModel = new RSTableModel();
      TableDefinition tableDefinition = new TableDefinition(Arrays.asList(header, ""),
            Arrays.asList(String.class, String.class));
      tableModel.setTableDefinition(tableDefinition);
      tableDefinition.setDisplaySizes(Arrays.asList(150, 0));
      results.keySet().forEach(
            key -> tableModel.addDataRow(
                  new RSStringTableRow(key, format(results.get(key)))));
      return tableModel;
   }
   
   private String format(Object o) {
      if (null == o)
         return "";
      
      if (o instanceof String)
         return (String) o;
      else if (o instanceof List) 
         return String.join(", ", (List)o);
      else return o.toString();
   }
   
   private RSTableModel generateTableModel(Map<String, String> tableMeta, String header, Map<String, Object> results,
         Optional<List<Integer>> colWidths) {
      RSTableModel tableModel = new RSTableModel();
      TableDefinition tableDefinition = new TableDefinition(Arrays.asList(header, ""),
            Arrays.asList(String.class, String.class));
      if (colWidths.isPresent())
         tableDefinition.setDisplaySizes(colWidths.get());

      tableModel.setTableDefinition(tableDefinition);
      List<String> keyList = new ArrayList<>((tableMeta.keySet()));
      Collections.sort(keyList);
      keyList.forEach(key -> {
         Object res = results.get(tableMeta.get(key));
         tableModel.addDataRow(new RSStringTableRow(key, null == res ? "null" : res.toString()));
      });
      return tableModel;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
