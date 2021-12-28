package net.datenwerke.dbpool.terminal.commands;

import static java.util.Comparator.comparing;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Map;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class ConnPoolStatsCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "connPoolStats";

   private final DbPoolService dbPoolService;

   @Inject
   public ConnPoolStatsCommand(DbPoolService dbPoolService) {
      this.dbPoolService = dbPoolService;
   }

   @Override
   @CliHelpMessage(messageClass = DatasourcesMessages.class, name = BASE_COMMAND, description = "commandConnPoolStats_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {

      CommandResult result = new CommandResult();

      Map<ConnectionPoolConfig, ComboPooledDataSource> poolMap = dbPoolService.getPoolMap();
      try {
         if (!poolMap.keySet().isEmpty()) {
            poolMap.keySet().stream().sorted(comparing(ConnectionPoolConfig::getDatasourceName))
                  .forEach(rethrowConsumer(connPoolConfig -> {
                     RSTableModel table = new RSTableModel();
                     TableDefinition td = new TableDefinition(Arrays.asList("Pool", ""),
                           Arrays.asList(String.class, String.class));
                     table.setTableDefinition(td);

                     String datasource = connPoolConfig.getDatasourceName()
                           + (null == connPoolConfig.getDatasourceId() ? ""
                                 : " (" + connPoolConfig.getDatasourceId() + ")");

                     table.addDataRow(new RSStringTableRow("Datasource:", datasource));
                     ComboPooledDataSource ds = poolMap.get(connPoolConfig);
                     table.addDataRow(new RSStringTableRow("Max Pool Size:",
                           NumberFormat.getIntegerInstance().format(ds.getMaxPoolSize())));
                     table.addDataRow(new RSStringTableRow("Number of connections:",
                           NumberFormat.getIntegerInstance().format(ds.getNumConnectionsDefaultUser())));
                     table.addDataRow(new RSStringTableRow("Busy connections:",
                           NumberFormat.getIntegerInstance().format(ds.getNumBusyConnectionsDefaultUser())));
                     table.addDataRow(new RSStringTableRow("Idle connections:",
                           NumberFormat.getIntegerInstance().format(ds.getNumIdleConnectionsDefaultUser())));
                     table.addDataRow(new RSStringTableRow("Threads awaiting connection checkout:",
                           NumberFormat.getIntegerInstance().format(ds.getNumThreadsAwaitingCheckoutDefaultUser())));
                     table.addDataRow(new RSStringTableRow("Unclosed orphaned connections:", NumberFormat
                           .getIntegerInstance().format(ds.getNumUnclosedOrphanedConnectionsDefaultUser())));

                     result.addResultTable(table);
                  }));
         } else {
            return new CommandResult("Connection pool is empty");
         }
      } catch (SQLException e) {
         return new CommandResult("Error while retrieving connection pool statistics");
      }

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

}
