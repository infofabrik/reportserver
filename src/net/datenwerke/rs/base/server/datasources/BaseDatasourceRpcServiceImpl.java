package net.datenwerke.rs.base.server.datasources;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.rpc.BaseDatasourceRpcService;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProviderImpl;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

@Singleton
public class BaseDatasourceRpcServiceImpl extends SecuredRemoteServiceServlet implements BaseDatasourceRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -1991463672346224996L;

   private final DtoService dtoService;
   private final DBHelperService dbHelperService;
   private final SimpleDataSupplier simpleDataSupplyer;
   private final EntityClonerService entityCloner;
   private final SecurityService securityService;

   @Inject
   public BaseDatasourceRpcServiceImpl(DtoService dtoService, EntityClonerService entityCloner,
         DBHelperService dbHelperService, SimpleDataSupplier simpleDataSupplyer, SecurityService securityService) {

      /* store objects */
      this.dtoService = dtoService;
      this.entityCloner = entityCloner;
      this.dbHelperService = dbHelperService;
      this.simpleDataSupplyer = simpleDataSupplyer;
      this.securityService = securityService;
   }

   /**
    * May be called without having to log in.
    */
   @SecurityChecked(loginRequired = false)
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public List<DatabaseHelperDto> getDBHelperList() throws ServerCallFailedException {
      return dbHelperService.getDatabaseHelpers()
         .stream()
         .sorted(comparing(DatabaseHelper::getName))
         .map(dtoService::createDto)
         .map(databaseHelper -> (DatabaseHelperDto) databaseHelper)
         .collect(toList());
   }

   @Override
   public DatabaseHelperDto dummy(DatabaseHelperDto dto) {
      return null;

   }

   @Override
   public List<String> loadColumnDefinition(@Named("datasource") DatasourceContainerDto containerDto, String query)
         throws ServerCallFailedException {
      DatasourceContainer container = (DatasourceContainer) dtoService.loadPoso(containerDto);
      if (null == container)
         throw new ServerCallFailedException("No datasource container found");
      DatasourceDefinition datasource = container.getDatasource();
      if (null == datasource)
         throw new ServerCallFailedException("Container does not contain datasource");

      if (!securityService.checkRights(datasource, Read.class, Write.class, Execute.class))
         throw new ViolatedSecurityExceptionDto();

      container = entityCloner.cloneEntity(container);

      /* update query */
      DatasourceDefinitionConfig dsConfig = container.getDatasourceConfig();
      if (!(dsConfig instanceof DatabaseDatasourceConfig))
         throw new ServerCallFailedException("Expected Database");
      ((DatabaseDatasourceConfig) dsConfig).setQuery(query);

      try {
         return simpleDataSupplyer.getColumnNames(new DatasourceContainerProviderImpl(container));
      } catch (ReportExecutorException e) {
         throw new ServerCallFailedException(e);
      }
   }

   @Override
   public PagingLoadResult<ListStringBaseModel> loadData(DatasourceContainerDto containerDto,
         PagingLoadConfig loadConfig, String query) throws ServerCallFailedException {
      DatasourceContainer container = (DatasourceContainer) dtoService.loadPoso(containerDto);
      if (null == container)
         throw new ServerCallFailedException("No datasource container found");
      DatasourceDefinition datasource = container.getDatasource();
      if (null == datasource)
         throw new ServerCallFailedException("Container does not contain datasource");

      if (!securityService.checkRights(datasource, Read.class, Write.class, Execute.class))
         throw new ViolatedSecurityExceptionDto();

      /* update query */
      DatasourceDefinitionConfig dsConfig = container.getDatasourceConfig();
      if (!(dsConfig instanceof DatabaseDatasourceConfig))
         throw new ServerCallFailedException("Expected Database");
      ((DatabaseDatasourceConfig) dsConfig).setQuery(query);

      try {
         RSTableModel tableModel = simpleDataSupplyer.getData(new DatasourceContainerProviderImpl(container), null,
               loadConfig.getOffset(), loadConfig.getLimit());
         int count = simpleDataSupplyer.getDataCount(new DatasourceContainerProviderImpl(container));

         List<ListStringBaseModel> list = new ArrayList<ListStringBaseModel>();
         for (RSTableRow row : tableModel.getData())
            list.add(new ListStringBaseModel(row.getRow()));

         return new PagingLoadResultBean<ListStringBaseModel>(list, count, loadConfig.getOffset());
      } catch (ReportExecutorException e) {
         throw new ServerCallFailedException(e);
      }
   }

}
