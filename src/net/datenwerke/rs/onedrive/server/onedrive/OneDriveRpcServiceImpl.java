package net.datenwerke.rs.onedrive.server.onedrive;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTestFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.rpc.OneDriveRpcService;
import net.datenwerke.rs.onedrive.service.onedrive.OneDriveService;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class OneDriveRpcServiceImpl extends SecuredRemoteServiceServlet implements OneDriveRpcService {
   /**
    * 
    */
   private static final long serialVersionUID = -8603399725365820217L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final OneDriveService oneDriveService;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final ZipUtilsService zipUtilsService;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public OneDriveRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         OneDriveService oneDriveService, 
         ExceptionServices exceptionServices,
         ZipUtilsService zipUtilsService,
         Provider<DatasinkService> datasinkServiceProvider
         ) {

      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.hookHandlerService = hookHandlerService;
      this.oneDriveService = oneDriveService;
      this.exceptionServices = exceptionServices;
      this.zipUtilsService = zipUtilsService;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoOneDrive(ReportDto reportDto, String executorToken, OneDriveDatasinkDto oneDriveDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed)
         throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      OneDriveDatasink oneDriveDatasink = (OneDriveDatasink) dtoService.loadPoso(oneDriveDatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(oneDriveDatasink, Read.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      hookHandlerService.getHookers(ReportExportViaSessionHook.class)
            .forEach(hooker -> hooker.adjustReport(toExecute, configArray));

      CompiledReport cReport;
      try {
         cReport = reportExecutorService.execute(toExecute, format, configArray);

         if (compressed) {
            String filename = name + ".zip";
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = cReport.getReport();
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(toExecute.getName() + "." + cReport.getFileExtension()),
                     reportObj, os);
               oneDriveService.exportIntoDatasink(os.toByteArray(), oneDriveDatasink,
                     new DatasinkFilenameFolderConfig() {

                        @Override
                        public String getFilename() {
                           return filename;
                        }

                        @Override
                        public String getFolder() {
                           return folder;
                        }

                     });
            }
         } else {
            String filename = name + "." + cReport.getFileExtension();
            oneDriveService.exportIntoDatasink(cReport.getReport(), oneDriveDatasink,
                  new DatasinkFilenameFolderConfig() {

                     @Override
                     public String getFilename() {
                        return filename;
                     }

                     @Override
                     public String getFolder() {
                        return folder;
                     }

                  });
         }
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to OneDrive: " + e.getMessage(), e);
      }

   }

   private ReportExecutionConfig[] getConfigArray(final String executorToken,
         final List<ReportExecutionConfigDto> configs) throws ExpectedException {
      return Stream.concat(
            configs.stream().map(rethrowFunction(config -> (ReportExecutionConfig) dtoService.createPoso(config))),
            Stream.of(new RECReportExecutorToken(executorToken))).toArray(ReportExecutionConfig[]::new);
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException {
      return datasinkServiceProvider.get().getEnabledConfigs(oneDriveService);
   }

   @Override
   public boolean testOneDriveDatasink(OneDriveDatasinkDto oneDriveDatasinkDto) throws ServerCallFailedException {
      OneDriveDatasink oneDriveDatasink = (OneDriveDatasink) dtoService.loadPoso(oneDriveDatasinkDto);

      /* check rights */
      securityService.assertRights(oneDriveDatasink, Read.class, Execute.class);

      try {
         oneDriveService.testDatasink(oneDriveDatasink);
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {

      Optional<OneDriveDatasink> defaultDatasink = oneDriveService.getDefaultDatasink();
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }
   
   @Override
   public void exportFileIntoDatasink(FileServerFileDto fileDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder) throws ServerCallFailedException {
      
      OneDriveDatasink oneDriveDatasink = (OneDriveDatasink) dtoService.loadPoso(datasinkDto);
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      
      /* check rights */
      securityService.assertRights(file, Read.class);
      securityService.assertRights(oneDriveDatasink, Read.class, Execute.class);
      
      try {
         oneDriveService.exportIntoDatasink(file.getData(), oneDriveDatasink, new DatasinkFilenameFolderConfig() {

            @Override
            public String getFilename() {
               return filename;
            }

            @Override
            public String getFolder() {
               return folder;
            }

         });
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to OneDrive: " + e.getMessage(), e);
      }
   }
}