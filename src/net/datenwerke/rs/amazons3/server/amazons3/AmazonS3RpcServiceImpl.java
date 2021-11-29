package net.datenwerke.rs.amazons3.server.amazons3;

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
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.rpc.AmazonS3RpcService;
import net.datenwerke.rs.amazons3.service.amazons3.AmazonS3Service;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTestFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class AmazonS3RpcServiceImpl extends SecuredRemoteServiceServlet implements AmazonS3RpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -2851245535282732136L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final AmazonS3Service amazonS3Service;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final ZipUtilsService zipUtilsService;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public AmazonS3RpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         AmazonS3Service amazonS3Service, 
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
      this.amazonS3Service = amazonS3Service;
      this.exceptionServices = exceptionServices;
      this.zipUtilsService = zipUtilsService;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoAmazonS3(ReportDto reportDto, String executorToken, AmazonS3DatasinkDto amazonS3DatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, final String folder, boolean compressed)
         throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      AmazonS3Datasink amazonS3Datasink = (AmazonS3Datasink) dtoService.loadPoso(amazonS3DatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(amazonS3Datasink, Read.class, Execute.class);

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
               datasinkServiceProvider.get().exportIntoDatasink(os.toByteArray(), amazonS3Datasink, amazonS3Service,
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
            datasinkServiceProvider.get().exportIntoDatasink(cReport.getReport(), amazonS3Datasink, amazonS3Service,
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
         throw new ServerCallFailedException("Could not send to AmazonS3: " + e.getMessage(), e);
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
      return datasinkServiceProvider.get().getEnabledConfigs(amazonS3Service);
   }

   @Override
   public boolean testAmazonS3Datasink(AmazonS3DatasinkDto amazonS3DatasinkDto) throws ServerCallFailedException {
      AmazonS3Datasink amazonS3Datasink = (AmazonS3Datasink) dtoService.loadPoso(amazonS3DatasinkDto);

      /* check rights */
      securityService.assertRights(amazonS3Datasink, Read.class, Execute.class);

      try {
         datasinkServiceProvider.get().testDatasink(amazonS3Datasink, amazonS3Service, new DatasinkFilenameFolderConfig() {
            
            @Override
            public String getFolder() {
               return amazonS3Datasink.getFolder();
            }
            
            @Override
            public String getFilename() {
               return "reportserver-amazonS3-test.txt";
            }
         });
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {

      Optional<? extends DatasinkDefinition> defaultDatasink = datasinkServiceProvider.get()
            .getDefaultDatasink(amazonS3Service);
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

   @Override
   public void exportFileIntoDatasink(FileServerFileDto fileDto, DatasinkDefinitionDto datasinkDto, final String filename,
         final String folder) throws ServerCallFailedException {
      
      AmazonS3Datasink amazonS3Datasink = (AmazonS3Datasink) dtoService.loadPoso(datasinkDto);
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      
      /* check rights */
      securityService.assertRights(file, Read.class);
      securityService.assertRights(amazonS3Datasink, Read.class, Execute.class);
      
      try {
         datasinkServiceProvider.get().exportIntoDatasink(file.getData(), amazonS3Datasink, amazonS3Service,
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
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to AmazonS3: " + e.getMessage(), e);
      }
   }

}
