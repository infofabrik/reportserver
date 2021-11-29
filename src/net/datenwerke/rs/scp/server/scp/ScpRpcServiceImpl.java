package net.datenwerke.rs.scp.server.scp;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

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
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.rpc.ScpRpcService;
import net.datenwerke.rs.scp.service.scp.ScpService;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class ScpRpcServiceImpl extends SecuredRemoteServiceServlet implements ScpRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 7162071775158618675L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final ScpService scpService;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final ZipUtilsService zipUtilsService;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public ScpRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         ScpService scpService, 
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
      this.scpService = scpService;
      this.exceptionServices = exceptionServices;
      this.zipUtilsService = zipUtilsService;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void exportIntoScp(ReportDto reportDto, String executorToken, ScpDatasinkDto scpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed) throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      ScpDatasink scpDatasink = (ScpDatasink) dtoService.loadPoso(scpDatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

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
               scpService.exportIntoDatasink(os.toByteArray(), scpDatasink, new DatasinkFilenameFolderConfig() {

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
            scpService.exportIntoDatasink(cReport.getReport(), scpDatasink, new DatasinkFilenameFolderConfig() {

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
         throw new ServerCallFailedException("Could not send report to Scp server: " + e.getMessage(), e);
      }

   }

   private ReportExecutionConfig[] getConfigArray(final String executorToken,
         final List<ReportExecutionConfigDto> configs) throws ExpectedException {
      return Stream.concat(
            configs.stream().map(rethrowFunction(config -> (ReportExecutionConfig) dtoService.createPoso(config))),
            Stream.of(new RECReportExecutorToken(executorToken))).toArray(ReportExecutionConfig[]::new);
   }

   @Override
   public Map<StorageType, Boolean> getScpEnabledConfigs() throws ServerCallFailedException {
      return datasinkServiceProvider.get().getEnabledConfigs(scpService);
   }

   @Override
   public boolean testScpDatasink(ScpDatasinkDto scpDatasinkDto) throws ServerCallFailedException {
      ScpDatasink scpDatasink = (ScpDatasink) dtoService.loadPoso(scpDatasinkDto);

      /* check rights */
      securityService.assertRights(scpDatasink, Read.class, Execute.class);

      try {
         scpService.testScpDatasink(scpDatasink);
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }
   
   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {

      Optional<ScpDatasink> defaultDatasink = scpService.getDefaultDatasink();
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }
   
   @Override
   public void exportFileIntoDatasink(FileServerFileDto fileDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder) throws ServerCallFailedException {
      
      ScpDatasink scpDatasink = (ScpDatasink) dtoService.loadPoso(datasinkDto);
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      
      /* check rights */
      securityService.assertRights(file, Read.class);
      securityService.assertRights(scpDatasink, Read.class, Execute.class);
      
      try {
         scpService.exportIntoDatasink(file.getData(), scpDatasink, new DatasinkFilenameFolderConfig() {

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
         throw new ServerCallFailedException("Could not send to SCP: " + e.getMessage(), e);
      }
   }

}
