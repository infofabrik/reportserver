package net.datenwerke.rs.printer.server.printer;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.nio.charset.StandardCharsets;
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
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.shared.fileserver.FileServerFileHelper;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.printer.client.printer.rpc.PrinterRpcService;
import net.datenwerke.rs.printer.service.printer.PrinterService;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class PrinterRpcServiceImpl extends SecuredRemoteServiceServlet implements PrinterRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 2023080396191289670L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<PrinterService> printerServiceProvider;

   @Inject
   public PrinterRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         ExceptionServices exceptionServices,
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<PrinterService> printerServiceProvider
         ) {

      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.hookHandlerService = hookHandlerService;
      this.exceptionServices = exceptionServices;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.printerServiceProvider = printerServiceProvider;
   }

   @Override
   public void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, boolean compressed)
         throws ServerCallFailedException {
      if (!(datasinkDto instanceof PrinterDatasinkDto))
         throw new IllegalArgumentException("Not a printer datasink");

      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      PrinterDatasink printerDatasink = (PrinterDatasink) dtoService.loadPoso(datasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(printerDatasink, Read.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      hookHandlerService.getHookers(ReportExportViaSessionHook.class)
            .forEach(hooker -> hooker.adjustReport(toExecute, configArray));

      CompiledReport cReport;
      try {
         cReport = reportExecutorService.execute(toExecute, format, configArray);

         String filename = name + "." + cReport.getFileExtension();
         datasinkServiceProvider.get().exportIntoDatasink(cReport.getReport(), printerDatasink,
               new DatasinkFilenameConfig() {
                  @Override
                  public String getFilename() {
                     return filename;
                  }
               });
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to Printer datasink: " + e.getMessage(), e);
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
      return datasinkServiceProvider.get().getEnabledConfigs(printerServiceProvider.get());
   }

   @Override
   public boolean testPrinterDatasink(PrinterDatasinkDto printerDatasinkDto) throws ServerCallFailedException {
      PrinterDatasink printerDatasink = (PrinterDatasink) dtoService.loadPoso(printerDatasinkDto);

      /* check rights */
      securityService.assertRights(printerDatasink, Read.class, Execute.class);

      try {
         datasinkServiceProvider.get().testDatasink(printerDatasink, new DatasinkConfiguration() {
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
            .getDefaultDatasink(printerServiceProvider.get());
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto,
         final String filename, boolean compressed) throws ServerCallFailedException {
      /* check rights */
      securityService.assertRights(abstractNodeDto, Read.class);
      securityService.assertRights(datasinkDto, Read.class, Execute.class);
      
      if (abstractNodeDto instanceof FileServerFileDto) {
         DatasinkDefinition datasink = (DatasinkDefinition) dtoService.loadPoso(datasinkDto);
         FileServerFile fileObj = (FileServerFile) dtoService.loadPoso(abstractNodeDto);
         if (! new FileServerFileHelper().isTextFile(fileObj.getName(), fileObj.getContentType()))
            throw new ServerCallFailedException("Only printing text files is supported");
         
         if (null == fileObj.getData())
            throw new ServerCallFailedException("File content is empty");
         
         try {
            datasinkServiceProvider.get().exportIntoDatasink(new String(fileObj.getData(), StandardCharsets.UTF_8), datasink,
                  new DatasinkConfiguration(){});
         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the file: " + e.getMessage(), e);
         }
      } else if (abstractNodeDto instanceof FileServerFolderDto) {
         throw new ServerCallFailedException("Sending folders to printer datasink is not supported");
      }
   }
   
   @Override
   public List<String> getAvailablePrinters() throws ServerCallFailedException {
      return printerServiceProvider.get().getAvailablePrinters();
   }

}
