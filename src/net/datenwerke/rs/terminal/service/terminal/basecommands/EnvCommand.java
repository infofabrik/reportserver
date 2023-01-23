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
      table.addDataRow(new RSStringTableRow("Java home", generalInfo.getJavaHome()));
      table.addDataRow(new RSStringTableRow("JVM Args", generalInfo.getVmArguments()));
      table.addDataRow(new RSStringTableRow("Application server", generalInfo.getApplicationServer()));
      table.addDataRow(new RSStringTableRow("Catalina home", generalInfo.getCatalinaHome()));
      table.addDataRow(new RSStringTableRow("Catalina base", generalInfo.getCatalinaBase()));
      table.addDataRow(new RSStringTableRow("Log files directory", generalInfo.getLogFilesDirectory()));
      table.addDataRow(new RSStringTableRow("Max memory", generalInfo.getMaxMemory()));
      table.addDataRow(new RSStringTableRow("Configuration directory", generalInfo.getConfigDir()));
      table.addDataRow(new RSStringTableRow("Groovy version", generalInfo.getGroovyVersion()));
      table.addDataRow(new RSStringTableRow("Locale", generalInfo.getLocale()));
      table.addDataRow(new RSStringTableRow("JVM locale", generalInfo.getJvmLocale()));
      table.addDataRow(new RSStringTableRow("JVM user language", generalInfo.getJvmUserLanguage()));
      table.addDataRow(new RSStringTableRow("JVM user country", generalInfo.getJvmUserCountry()));
      table.addDataRow(new RSStringTableRow("JVM user timezone", generalInfo.getJvmUserTimezone()));
      table.addDataRow(new RSStringTableRow("JVM file encoding", generalInfo.getJvmFileEncoding()));
      table.addDataRow(new RSStringTableRow("Operation system", generalInfo.getOsVersion()));
      table.addDataRow(new RSStringTableRow("User agent", generalInfo.getUserAgent()));
      table.addDataRow(new RSStringTableRow("Request URL", generalInfo.getRequestURL()));
      table.addDataRow(new RSStringTableRow("Request scheme", generalInfo.getScheme()));
      table.addDataRow(new RSStringTableRow("Request server name", generalInfo.getServerName()));
      table.addDataRow(new RSStringTableRow("Request server port",
            generalInfo.getServerPort() != -1 ? generalInfo.getServerPort() + "" : ""));
      table.addDataRow(new RSStringTableRow("Request context path", generalInfo.getContextPath()));
      table.addDataRow(new RSStringTableRow("Request protocol", generalInfo.getProtocol()));

      result.addResultTable(table);
      try {
         result.addResultTable(getPamsAsTable(generalInfo));
         result.addResultTable(getDbConfigAsTable(generalInfo));
         result.addResultTable(getInternalDbInformationAsTable(generalInfo));
         result.addResultTable(getSftpInformationAsTable(generalInfo));
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
   
   private RSTableModel getSftpInformationAsTable(GeneralInfoDto generalInfo) throws SQLException {
      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("SFTP server", ""),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);
      td.setDisplaySizes(Arrays.asList(150, 0));

      
      if (!generalInfo.isSftpEnabled()) {
         table.addDataRow(new RSStringTableRow("Disabled", ""));
         return table;
      }
      
      table.addDataRow(new RSStringTableRow("Key", generalInfo.getSftpKey()));
      table.addDataRow(new RSStringTableRow("Port", generalInfo.getSftpPort() + ""));

      return table;
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
      table.addDataRow(new RSStringTableRow("hibernate.default_schema", generalInfo.getHibernateDefaultSchema()));
      table.addDataRow(new RSStringTableRow("Database name",  generalInfo.getHibernateDbDatabaseName()));
      table.addDataRow(new RSStringTableRow("Database version",  generalInfo.getHibernateDbVersion()));
      table.addDataRow(new RSStringTableRow("JDBC driver name",  generalInfo.getHibernateDbDriverName()));
      table.addDataRow(new RSStringTableRow("JDBC driver version",  generalInfo.getHibernateDbDriverVersion()));
      table.addDataRow(new RSStringTableRow("JDBC major version",  generalInfo.getHibernateDbJdbcMajorVersion()));
      table.addDataRow(new RSStringTableRow("JDBC minor version",  generalInfo.getHibernateDbJdbcMinorVersion()));
      table.addDataRow(new RSStringTableRow("JDBC URL",  generalInfo.getHibernateDbJdbcUrl()));
      table.addDataRow(new RSStringTableRow("JDBC username",  generalInfo.getHibernateDbJdbcUsername()));

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
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("ID", generalInfo.getInternalDbId()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("Path",  generalInfo.getInternalDbPath()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("Database name",  generalInfo.getInternalDbDatabaseName()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("Database version",  generalInfo.getInternalDbVersion()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC driver name",  generalInfo.getInternalDbDriverName()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC driver version",  generalInfo.getInternalDbDriverVersion()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC major version",  generalInfo.getInternalDbJdbcMajorVersion()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC minor version",  generalInfo.getInternalDbJdbcMinorVersion()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC URL",  generalInfo.getInternalDbJdbcUrl()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC username",  generalInfo.getInternalDbUsername()));
      if (generalInfo.isInternalDbConfigured())
         table.addDataRow(new RSStringTableRow("JDBC properties",
               null != generalInfo.getInternalDbJdbcProperties() ? generalInfo.getInternalDbJdbcProperties().toString()
                     : null));

      return table;
   }
   
}
