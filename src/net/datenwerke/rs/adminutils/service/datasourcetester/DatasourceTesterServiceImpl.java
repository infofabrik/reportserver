package net.datenwerke.rs.adminutils.service.datasourcetester;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.utils.reflection.ReflectionService;

public class DatasourceTesterServiceImpl implements DatasourceTesterService {

   private final DBHelperService dbHelperService;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;
   private final Provider<DbPoolService> dbPoolServiceProvider;
   private final Provider<ReflectionService> reflectionServiceProvider;
   private final Provider<SimpleDataSupplier> simpleDataSupplierProvider;

   @Inject
   public DatasourceTesterServiceImpl(
         DBHelperService dbHelperService, 
         Provider<HookHandlerService> hookHandlerServiceProvider,
         Provider<DbPoolService> dbPoolServiceProvider,
         Provider<ReflectionService> reflectionServiceProvider,
         Provider<SimpleDataSupplier> simpleDataSupplierProvider
         ) {
      this.dbHelperService = dbHelperService;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
      this.dbPoolServiceProvider = dbPoolServiceProvider;
      this.reflectionServiceProvider = reflectionServiceProvider;
      this.simpleDataSupplierProvider = simpleDataSupplierProvider;
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
         final ReflectionService reflectionService = reflectionServiceProvider.get();
         Method isValidMethod = reflectionService.getMethod(Connection.class, "isValid", int.class);
         if (null != isValidMethod && ! reflectionService.isAbstract(isValidMethod)) {
            return connection.isValid(60);
         } else {
            /* create config */
            DatabaseDatasourceConfig config = (DatabaseDatasourceConfig) datasource.createConfigObject();
            config.setQuery(dbHelper.createDummyQuery());

            /* build dummy table report */
            TableReport report = new TableReport();
            DatasourceContainer datasourceContainer = new DatasourceContainer(datasource, config);
            report.setDatasourceContainer(datasourceContainer);

            simpleDataSupplierProvider.get().getData(report);
            return true;
         }
      } catch (Exception e) {
         throw new ConnectionTestFailedException(ExceptionUtils.getRootCauseMessage(e), e);
      }
   }

}
