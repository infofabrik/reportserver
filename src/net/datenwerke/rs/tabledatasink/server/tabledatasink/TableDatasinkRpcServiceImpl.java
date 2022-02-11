package net.datenwerke.rs.tabledatasink.server.tabledatasink;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.rpc.TableDatasinkRpcService;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.TableDatasinkService;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class TableDatasinkRpcServiceImpl extends SecuredRemoteServiceServlet implements TableDatasinkRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -8891770720145863431L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final TableDatasinkService tableDatasinkService;
   private final SecurityService securityService;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final DbPoolService dbPoolService;
   private final DBHelperService dbHelperService;

   @Inject
   public TableDatasinkRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService,
         DtoService dtoService, 
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         TableDatasinkService tableDatasinkService,
         ExceptionServices exceptionServices, 
         Provider<DatasinkService> datasinkServiceProvider,
         DbPoolService dbPoolService,
         DBHelperService dbHelperService
         ) {

      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.tableDatasinkService = tableDatasinkService;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.dbPoolService = dbPoolService;
      this.dbHelperService = dbHelperService;
   }

   @Override
   public void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed)
         throws ServerCallFailedException {
      if (!(datasinkDto instanceof TableDatasinkDto))
         throw new IllegalArgumentException("Not a table datasink");

      TableDatasink tableDatasink = (TableDatasink) dtoService.loadPoso(datasinkDto);
      Objects.requireNonNull(tableDatasink, "Datasink is null");

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());
      
      String srcQuery = ((DatabaseDatasourceConfig) referenceReport.getDatasourceContainer().getDatasourceConfig())
            .getQuery();
      Objects.requireNonNull(srcQuery, "Query is empty");
      if ("".equals(srcQuery.trim())) 
         throw new IllegalArgumentException("Query is empty");
      
      DatasourceContainer dstDatasourceContainer = tableDatasink.getDatasourceContainer();
      Objects.requireNonNull(dstDatasourceContainer);
      Objects.requireNonNull(dstDatasourceContainer.getDatasource());
      if (!(dstDatasourceContainer.getDatasource() instanceof DatabaseDatasource))
         throw new IllegalArgumentException("Only database datasources are currently supported");
      
      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(tableDatasink, Read.class, Execute.class);
      securityService.assertRights(dstDatasourceContainer.getDatasource(), Read.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);
      
      if (!(toExecute instanceof TableReport)) 
         throw new IllegalArgumentException("Not a dynamic list");
      
      DatabaseDatasource dstDatasource = (DatabaseDatasource) dstDatasourceContainer.getDatasource();
      
      TableReport tableReport = (TableReport) toExecute;
      
      Objects.requireNonNull(tableReport.getDatasourceContainer().getDatasource());
      if (!(tableReport.getDatasourceContainer().getDatasource() instanceof DatabaseDatasource))
         throw new IllegalArgumentException("Only database datasources are currently supported");
      
      try {
//         Connection connection = (Connection) dbPoolService.getConnection(dstDatasource.getConnectionConfig()).get();
//         TableDBDataSource tableDBDatasource = new TableDBDataSource(connection, srcQuery, tableDatasink,
//               dbHelperService.getDatabaseHelper(connection));
         
         datasinkServiceProvider.get().exportIntoDatasink(tableReport, tableDatasink, tableDatasinkService, null);
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to Table Datasink: " + e.getMessage(), e);
      }
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException {
      return datasinkServiceProvider.get().getEnabledConfigs(tableDatasinkService);
   }

   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {
      Optional<? extends DatasinkDefinition> defaultDatasink = datasinkServiceProvider.get()
            .getDefaultDatasink(tableDatasinkService);
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto,
         String filename, String folder, boolean compressed) throws ServerCallFailedException {
      /* check rights */
      securityService.assertRights(abstractNodeDto, Read.class);
      securityService.assertRights(datasinkDto, Read.class, Execute.class);
      datasinkServiceProvider.get().exportFileIntoDatasink(abstractNodeDto, datasinkDto, tableDatasinkService, filename,
            folder, compressed);

   }

}
