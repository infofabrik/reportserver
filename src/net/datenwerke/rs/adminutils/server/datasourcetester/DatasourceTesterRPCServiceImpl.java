package net.datenwerke.rs.adminutils.server.datasourcetester;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.adminutils.client.datasourcetester.rpc.DatasourceTesterRPCService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.client.datasources.hooks.DatabaseConnectionTesterHook;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class DatasourceTesterRPCServiceImpl extends SecuredRemoteServiceServlet implements DatasourceTesterRPCService {

   /**
    * 
    */
   private static final long serialVersionUID = 3945467345321105023L;

   private final DtoService dtoService;
   private final DBHelperService dbHelperService;
   private final SimpleDataSupplier simpleDataSupplyer;
   private final ExceptionServices exceptionServices;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;

   @Inject
   public DatasourceTesterRPCServiceImpl(
         DtoService dtoService, 
         DBHelperService dbHelperService,
         SimpleDataSupplier simpleDataSupplyer, 
         ExceptionServices exceptionServices,
         Provider<HookHandlerService> hookHandlerServiceProvider
         ) {

      /* store objects */
      this.dtoService = dtoService;
      this.dbHelperService = dbHelperService;
      this.simpleDataSupplyer = simpleDataSupplyer;
      this.exceptionServices = exceptionServices;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(
               name = "datasource", 
               isDto = true, 
               verify = @RightsVerification(
                     rights = { 
                           Read.class,
                           Execute.class 
                           })) })
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public boolean testConnection(@Named("datasource") DatabaseDatasourceDto databaseDto)
         throws ConnectionTestFailedException {
      /* create real datasource */
      final DatabaseDatasource datasource = (DatabaseDatasource) dtoService.loadPoso(databaseDto);

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

      try {
         simpleDataSupplyer.getData(report);
      } catch (Exception e) {
         ConnectionTestFailedException ex = new ConnectionTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

}
