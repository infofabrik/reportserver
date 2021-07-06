package net.datenwerke.rs.adminutils.service.datasourcetester;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

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

public class DatasourceTesterServiceImpl implements DatasourceTesterService {

   private final DBHelperService dbHelperService;
   private final SimpleDataSupplier simpleDataSupplyer;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;
   
   @Inject
   public DatasourceTesterServiceImpl(
         DBHelperService dbHelperService,
         SimpleDataSupplier simpleDataSupplyer,
         Provider<HookHandlerService> hookHandlerServiceProvider
         ) {
      this.dbHelperService = dbHelperService;
      this.simpleDataSupplyer = simpleDataSupplyer;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
   }

   @Override
   public boolean testConnection(DatabaseDatasource datasource) throws ConnectionTestFailedException, ReportExecutorException {
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

      /* create config */
      DatabaseDatasourceConfig config = (DatabaseDatasourceConfig) datasource.createConfigObject();
      config.setQuery(dbHelper.createDummyQuery());

      /* build dummy table report */
      TableReport report = new TableReport();
      DatasourceContainer datasourceContainer = new DatasourceContainer(datasource, config);
      report.setDatasourceContainer(datasourceContainer);

      simpleDataSupplyer.getData(report);

      return true;
   }

}
