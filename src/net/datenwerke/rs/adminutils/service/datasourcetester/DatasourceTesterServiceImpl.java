package net.datenwerke.rs.adminutils.service.datasourcetester;

import java.sql.Connection;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.base.client.datasources.hooks.DatabaseConnectionTesterHook;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;

public class DatasourceTesterServiceImpl implements DatasourceTesterService {

   private final DBHelperService dbHelperService;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;
   private final Provider<DbPoolService> dbPoolServiceProvider;

   @Inject
   public DatasourceTesterServiceImpl(
         DBHelperService dbHelperService, 
         Provider<HookHandlerService> hookHandlerServiceProvider,
         Provider<DbPoolService> dbPoolServiceProvider
         ) {
      this.dbHelperService = dbHelperService;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
      this.dbPoolServiceProvider = dbPoolServiceProvider;
   }

   @Override
   public boolean testConnection(DatabaseDatasource datasource)
         throws ConnectionTestFailedException, ReportExecutorException {
      /* get db helper */
      final DatabaseHelper dbHelper = dbHelperService.getDatabaseHelper(datasource.getDatabaseDescriptor());
      if (null == dbHelper)
         throw new ConnectionTestFailedException("No database type specified"); //$NON-NLS-1$

      /* check if anybody wants to take over */
      Optional<DatabaseConnectionTesterHook> takesOver = hookHandlerServiceProvider.get()
            .getHookers(DatabaseConnectionTesterHook.class)
            .stream()
            .filter(tester -> tester.consumes(datasource, dbHelper))
            .findAny();

      if (takesOver.isPresent())
         return takesOver.get().testConnection(datasource, dbHelper);
      
      try {
         Connection connection = (Connection) dbPoolServiceProvider.get().getConnection(datasource.getConnectionConfig()).get();
         return connection.isValid(60);
      } catch (Exception e) {
         throw new ConnectionTestFailedException(ExceptionUtils.getRootCauseMessage(e), e);
      }
   }

}
