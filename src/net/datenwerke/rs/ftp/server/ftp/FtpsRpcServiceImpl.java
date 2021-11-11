package net.datenwerke.rs.ftp.server.ftp;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTestFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.rpc.FtpsRpcService;
import net.datenwerke.rs.ftp.service.ftp.FtpService;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class FtpsRpcServiceImpl extends SecuredRemoteServiceServlet implements FtpsRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 366549503536730298L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final FtpService ftpService;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final ZipUtilsService zipUtilsService;

   @Inject
   public FtpsRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         FtpService ftpService, 
         ExceptionServices exceptionServices,
         ZipUtilsService zipUtilsService
         ) {

      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.hookHandlerService = hookHandlerService;
      this.ftpService = ftpService;
      this.exceptionServices = exceptionServices;
      this.zipUtilsService = zipUtilsService;
   }

   private ReportExecutionConfig[] getConfigArray(final String executorToken,
         final List<ReportExecutionConfigDto> configs) throws ExpectedException {
      return Stream.concat(
               configs
               .stream()
               .map(rethrowFunction(config -> (ReportExecutionConfig) dtoService.createPoso(config))),
            Stream.of(new RECReportExecutorToken(executorToken)))
            .toArray(ReportExecutionConfig[]::new);
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException {
      Map<StorageType, Boolean> enabledConfigs = new HashMap<>();

      enabledConfigs.putAll(ftpService.getFtpEnabledConfigs());
      enabledConfigs.putAll(ftpService.getSftpEnabledConfigs());
      enabledConfigs.putAll(ftpService.getFtpsEnabledConfigs());

      return enabledConfigs;
   }

   @Override
   public void exportIntoFtps(ReportDto reportDto, String executorToken, FtpsDatasinkDto ftpsDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed) throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      FtpsDatasink ftpsDatasink = (FtpsDatasink) dtoService.loadPoso(ftpsDatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(ftpsDatasink, Read.class, Execute.class);

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
   
               try {
                  zipUtilsService.createZip(
                        zipUtilsService.cleanFilename(toExecute.getName()) + "." + cReport.getFileExtension(),
                        reportObj, os);
               } catch (IOException e) {
                  throw new ServerCallFailedException(e);
               }
               ftpService.sendToFtpsServer(os.toByteArray(), ftpsDatasink, filename, folder);
            }
         } else {
            String filename = name + "." + cReport.getFileExtension();
            ftpService.sendToFtpsServer(cReport.getReport(), ftpsDatasink, filename, folder);
         }
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send report to FTPS server: " + e.getMessage(), e);
      }

   }

   @Override
   public boolean testFtpsDatasink(FtpsDatasinkDto ftpsDatasinkDto) throws ServerCallFailedException {
      FtpsDatasink ftpsDatasink = (FtpsDatasink) dtoService.loadPoso(ftpsDatasinkDto);

      /* check rights */
      securityService.assertRights(ftpsDatasink, Read.class, Execute.class);

      try {
         ftpService.testFtpsDataSink(ftpsDatasink);
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {
      Optional<FtpsDatasink> defaultDatasink = ftpService.getDefaultFtpsDatasink();
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

}
