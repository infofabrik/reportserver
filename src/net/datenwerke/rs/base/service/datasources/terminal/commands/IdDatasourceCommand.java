package net.datenwerke.rs.base.service.datasources.terminal.commands;

import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;

public class IdDatasourceCommand  implements TerminalCommandHook{
   
   public static final String BASE_COMMAND = "idDatasource";
   
   private final Provider<DatasourceHelperService> datasourceServiceProvider;
   
   private final Provider<TerminalService> terminalServiceProvider;
   
   private Map<String, String> generalInfomation = new HashMap<>();
   private Map<String, String> urlInfomation = new HashMap<>();
   private Map<String, String> functionsSection = new HashMap<>();
   private Map<String, String> supportsSection = new HashMap<>();
   
   @Inject
   public IdDatasourceCommand(
         Provider<DatasourceHelperService> datasourceServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.datasourceServiceProvider = datasourceServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      initMaps();
   }


   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   

   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() != 1)
         throw new IllegalArgumentException("exactly 1 argument required");
      String datasourceQuery = (String) nonOptionArguments.get(0);
      
      Map<String, List<String>> methodDescriptions = new HashMap<>();
      
      List<String> methodNames = new ArrayList<String>();
      methodNames.addAll(generalInfomation.values());
      methodNames.addAll(urlInfomation.values());
      methodNames.addAll(functionsSection.values());
      methodNames.addAll(supportsSection.values());
      
      methodNames.forEach(name -> methodDescriptions.put(name, new ArrayList<String>()));
      
      
      try {
         DatabaseDatasource datasource = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(DatabaseDatasource.class, datasourceQuery, session, Read.class);
         Map<String, Object> results = datasourceServiceProvider.get().fetchDatasourceMetadata(datasource, methodDescriptions);
         return generateCommandResult(results);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }


   private CommandResult generateCommandResult(Map<String, Object> results)  {
      ArrayList<RSTableModel> resultTables = new ArrayList<RSTableModel>();
      resultTables.add(generateRsTableModel(generalInfomation, "General information", results));
      resultTables.add(generateRsTableModel(urlInfomation, "URL information", results));
      resultTables.add(generateRsTableModel(functionsSection, "Functions section", results));
      resultTables.add(generateRsTableModel(supportsSection, "Supports section", results));
      
      CommandResult commandResult = new CommandResult();
      resultTables.forEach(table -> commandResult.addResultTable(table));
      return commandResult;
   }
   
   RSTableModel generateRsTableModel(Map<String, String> tableMeta, String header, Map<String, Object> results) {
      RSTableModel tableModel = new RSTableModel();
      TableDefinition tableDefinition = new TableDefinition(Arrays.asList(header, ""),
            Arrays.asList(String.class, String.class));
      tableModel.setTableDefinition(tableDefinition);
      List<String> keyList = new ArrayList<String>((tableMeta.keySet()));
      Collections.sort(keyList);
      keyList.forEach(
            key -> tableModel.addDataRow(new RSStringTableRow(key, results.get(tableMeta.get(key)).toString())));
      return tableModel;
   }


   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
   private void initMaps() {
      generalInfomation.put("Database name", "getDatabaseProductName");
      generalInfomation.put("Database version", "getDatabaseProductVersion");
      generalInfomation.put("Driver name", "getDriverName");
      generalInfomation.put("Driver version", "getDriverVersion");
      generalInfomation.put("JDBC major version", "getJDBCMajorVersion");
      generalInfomation.put("JDBC minor version", "getJDBCMinorVersion");
      
      urlInfomation.put("DBMS URL", "getURL");
      urlInfomation.put("User name", "getUserName");

      functionsSection.put("Numeric functions", "getNumericFunctions");
      functionsSection.put("String functions", "getStringFunctions");
      functionsSection.put("Time and date functions", "getTimeDateFunctions");
      functionsSection.put("System functions", "getSystemFunctions");
      
      Method[] methods = DatabaseMetaData.class.getMethods();
      Arrays.asList(methods).stream()
            .filter(method -> method.getParameterCount() == 0)
            .map(method -> method.getName())
            .filter(name -> name.startsWith("supports"))
            .forEach(name -> supportsSection.put(name, name));
   }
}