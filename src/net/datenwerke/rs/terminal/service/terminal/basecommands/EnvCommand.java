package net.datenwerke.rs.terminal.service.terminal.basecommands;

import static java.util.stream.Collectors.joining;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class EnvCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "env";
   private final Provider<GeneralInfoService> generalInfoServiceProvider;

   @Inject
   public EnvCommand(
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(
         messageClass = TerminalMessages.class, 
         name = BASE_COMMAND, 
         description = "commandEnv_description"
         )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      CommandResult result = new CommandResult();

      GeneralInfoDto generalInfo = generalInfoServiceProvider.get().getGeneralInfo();

      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("General Info", ""),
            Arrays.asList(String.class, String.class));
      td.setDisplaySizes(Arrays.asList(100, 0));
      table.setTableDefinition(td);

      table.addDataRow(new RSStringTableRow("Version", generalInfo.getRsVersion()));
      table.addDataRow(new RSStringTableRow("Java version", generalInfo.getJavaVersion()));
      table.addDataRow(new RSStringTableRow("JVM Args", generalInfo.getVmArguments()));
      table.addDataRow(new RSStringTableRow("Application server", generalInfo.getApplicationServer()));
      table.addDataRow(new RSStringTableRow("Max memory", generalInfo.getMaxMemory()));
      table.addDataRow(new RSStringTableRow("Configuration directory", generalInfo.getConfigDir()));
      table.addDataRow(new RSStringTableRow("Groovy version", generalInfo.getGroovyVersion()));
      table.addDataRow(new RSStringTableRow("Locale", generalInfo.getLocale()));
      table.addDataRow(new RSStringTableRow("JVM Locale", generalInfo.getJvmLocale()));
      table.addDataRow(new RSStringTableRow("Operation system", generalInfo.getOsVersion()));
      table.addDataRow(new RSStringTableRow("User agent", generalInfo.getUserAgent()));

      result.addResultTable(table);
      try {
         result.addResultTable(getPamsAsTable(generalInfo));
         result.addResultTable(getDbConfigAsTable(generalInfo));
         result.addResultTable(getInternalDbInformationAsTable(generalInfo));
         result.addResultTable(getSslInformationAsTable(generalInfo));
      } catch (SQLException e) {
         throw new TerminalException(e);
      }
      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
   private RSTableModel getSslInformationAsTable(GeneralInfoDto generalInfo) throws SQLException {
      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("SSL", ""),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);
      td.setDisplaySizes(Arrays.asList(150, 0));

      table.addDataRow(new RSStringTableRow("Supported SSL protocols",
            generalInfo.getSupportedSslProtocols().stream().collect(joining(", "))));
      table.addDataRow(new RSStringTableRow("Default SSL protocols",
            generalInfo.getDefaultSslProtocols().stream().collect(joining(", "))));
      table.addDataRow(new RSStringTableRow("Enabled SSL protocols",
            generalInfo.getEnabledSslProtocols().stream().collect(joining(", "))));

      return table;
   }
   
   private RSTableModel getPamsAsTable(GeneralInfoDto generalInfo) throws SQLException {
      final RSTableModel table = new RSTableModel();
      final TableDefinition td = new TableDefinition(Arrays.asList("Static PAM Configuration"),
            Arrays.asList(String.class));
      table.setTableDefinition(td);
      
      final List<String> staticPams = generalInfo.getStaticPams();
      if (staticPams.isEmpty()) {
         table.addDataRow(new RSStringTableRow("No static PAM configured"));
         return table;
      }
      
      staticPams.forEach(pam -> table.addDataRow(new RSStringTableRow(pam)));
      
      return table;
   }
   
   private RSTableModel getDbConfigAsTable(GeneralInfoDto generalInfo) {
      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("DB Config", ""),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);
      td.setDisplaySizes(Arrays.asList(220, 0));      

      table.addDataRow(new RSStringTableRow("hibernate.dialect", generalInfo.getHibernateDialect()));
      table.addDataRow(
            new RSStringTableRow("hibernate.connection.driver_class", generalInfo.getHibernateDriverClass()));
      table.addDataRow(new RSStringTableRow("hibernate.connection.url", generalInfo.getHibernateConnectionUrl()));
      table.addDataRow(
            new RSStringTableRow("hibernate.connection.username", generalInfo.getHibernateConnectionUsername()));
      table.addDataRow(new RSStringTableRow("hibernate.default_schema", generalInfo.getHibernateDefaultSchema()));

      table.addDataRow(new RSStringTableRow("Schema Version", generalInfo.getSchemaVersion())); 
      
      return table;
   }
   
   private RSTableModel getInternalDbInformationAsTable(GeneralInfoDto generalInfo) throws SQLException {
      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("Internal datasource info", ""),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);
      td.setDisplaySizes(Arrays.asList(150, 0));

      table.addDataRow(new RSStringTableRow("Name", generalInfo.getInternalDbDatasourceName()));
      if (null != generalInfo.getInternalDbId())
         table.addDataRow(new RSStringTableRow("ID", generalInfo.getInternalDbId()));
      if (null != generalInfo.getInternalDbPath())
         table.addDataRow(new RSStringTableRow("Path",  generalInfo.getInternalDbPath()));
      if (null != generalInfo.getInternalDbDatabaseName())
         table.addDataRow(new RSStringTableRow("Database name",  generalInfo.getInternalDbDatabaseName()));
      if (null != generalInfo.getInternalDbVersion())
         table.addDataRow(new RSStringTableRow("Database version",  generalInfo.getInternalDbVersion()));
      if (null != generalInfo.getInternalDbDriverName())
         table.addDataRow(new RSStringTableRow("JDBC driver name",  generalInfo.getInternalDbDriverName()));
      if (null != generalInfo.getInternalDbDriverVersion())
         table.addDataRow(new RSStringTableRow("JDBC driver version",  generalInfo.getInternalDbDriverVersion()));
      if (null != generalInfo.getInternalDbJdbcMajorVersion())
         table.addDataRow(new RSStringTableRow("JDBC major version",  generalInfo.getInternalDbJdbcMajorVersion()));
      if (null != generalInfo.getInternalDbJdbcMinorVersion())
         table.addDataRow(new RSStringTableRow("JDBC minor version",  generalInfo.getInternalDbJdbcMinorVersion()));
      if (null != generalInfo.getInternalDbJdbcUrl())
         table.addDataRow(new RSStringTableRow("JDBC URL",  generalInfo.getInternalDbJdbcUrl()));
      if (null != generalInfo.getInternalDbUsername())
         table.addDataRow(new RSStringTableRow("JDBC username",  generalInfo.getInternalDbUsername()));
      
      return table;
   }
   
}
