package net.datenwerke.rs.emaildatasink.server.emaildatasink;

import static java.util.stream.Collectors.toList;
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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.rpc.EmailDatasinkRpcService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.EmailDatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

@Singleton
public class EmailDatasinkRpcServiceImpl extends SecuredRemoteServiceServlet implements EmailDatasinkRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 4953913261676032725L;
   

   private final ReportService reportService;
   private final EmailDatasinkService emailDatasinkService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final SecurityService securityService;
   private final HookHandlerService hookHandlerService;
   private final ExceptionServices exceptionServices;
   private final UserManagerService userManagerService;
   private final ZipUtilsService zipUtilsService;

   @Inject
   public EmailDatasinkRpcServiceImpl(
         ReportService reportService, 
         EmailDatasinkService emailDatasinkService,
         DtoService dtoService, 
         ReportExecutorService reportExecutorService, 
         ReportDtoService reportDtoService,
         SecurityService securityService, 
         HookHandlerService hookHandlerService, 
         ExceptionServices exceptionServices,
         UserManagerService userManagerService,
         ZipUtilsService zipUtilsService
         ) {
      this.reportService = reportService;
      this.emailDatasinkService = emailDatasinkService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.reportDtoService = reportDtoService;
      this.securityService = securityService;
      this.hookHandlerService = hookHandlerService;
      this.exceptionServices = exceptionServices;
      this.userManagerService = userManagerService;
      this.zipUtilsService = zipUtilsService;
   }

   @Override
   public void exportToEmail(ReportDto reportDto, String executorToken, EmailDatasinkDto emailDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String subject, String message, boolean compressed,
         List<StrippedDownUser> recipients) throws ServerCallFailedException {

      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      EmailDatasink emailDatasink = (EmailDatasink) dtoService.loadPoso(emailDatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(emailDatasink, Read.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      hookHandlerService.getHookers(ReportExportViaSessionHook.class)
            .forEach(hooker -> hooker.adjustReport(toExecute, configArray));

      List<User> recipientUsers = recipients.stream().map(sUser -> (User) userManagerService.getNodeById(sUser.getId()))
            .filter(user -> null != user.getEmail() && !"".equals(user.getEmail())).collect(toList());

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
               emailDatasinkService.sendToEmailDatasink(os.toByteArray(), emailDatasink, subject, message, recipientUsers, filename, true);
            }
         } else {
            String filename = name + "." + cReport.getFileExtension();
            emailDatasinkService.sendToEmailDatasink(cReport.getReport(), emailDatasink, subject, message, recipientUsers,
                  filename, true);
         }
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send report to email: " + e.getMessage(), e);
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
      Map<StorageType, Boolean> enabledConfigs = new HashMap<>();
      enabledConfigs.putAll(emailDatasinkService.getEmailEnabledConfigs());
      return enabledConfigs;
   }

   @Override
   public boolean testEmailDatasink(EmailDatasinkDto emailDatasinkDto) throws ServerCallFailedException {
      EmailDatasink emailDatasink = (EmailDatasink) dtoService.loadPoso(emailDatasinkDto);

      /* check rights */
      securityService.assertRights(emailDatasink, Read.class, Execute.class);

      try {
         emailDatasinkService.testEmailDatasink(emailDatasink);
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {

      Optional<EmailDatasink> defaultDatasink = emailDatasinkService.getDefaultDatasink();
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

}
