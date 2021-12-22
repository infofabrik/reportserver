package net.datenwerke.rs.core.server.reportexport;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.rpc.ReportExporterRpcService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.events.ExportReportViaMailFailedEvent;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCache;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCacheEntry;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.filename.FileNameService;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;


/**
 * 
 *
 */
@Singleton
public class ReportExportRpcServiceImpl extends SecuredRemoteServiceServlet implements ReportExporterRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -7487071865510613059L;

   private static final String configPath = "exportfilemd/";

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final MailService mailService;
   private final UserManagerService userManagerService;
   private final EventBus eventBus;
   private final HookHandlerService hookHandlerService;
   private final ReportDtoService reportDtoService;
   private final SecurityService securityService;
   private final ReportService reportService;
   private final FileNameService fileNameService;
   private final Provider<ReportSessionCache> sessionCacheProvider;
   private final ConfigService configService;
   private final ReportServerService reportServerService;
   private final ZipUtilsService zipUtilsService;

   @Inject
   public ReportExportRpcServiceImpl(
         Provider<AuthenticatorService> authenticatorServiceProvider,
         ReportDtoService reportDtoService, 
         DtoService dtoService, 
         ReportExecutorService reportExecutorService,
         SecurityService securityService, 
         MailService mailService, 
         UserManagerService userManagerService,
         HookHandlerService hookHandlerService, 
         ReportService reportService, 
         EventBus eventBus,
         FileNameService fileNameService, 
         Provider<ReportSessionCache> sessionCacheProvider,
         ConfigService configService, 
         Provider<ConfigService> configServiceProvider,
         ReportServerService reportServerService,
         ZipUtilsService zipUtilsService) {

      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.mailService = mailService;
      this.userManagerService = userManagerService;
      this.hookHandlerService = hookHandlerService;
      this.reportService = reportService;
      this.eventBus = eventBus;
      this.fileNameService = fileNameService;
      this.sessionCacheProvider = sessionCacheProvider;
      this.configService = configService;
      this.reportServerService = reportServerService;
      this.zipUtilsService = zipUtilsService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void storeInSessionForExport(@Named("report") ReportDto reportDto, String executorToken, String format,
         List<ReportExecutionConfigDto> configs) throws ServerCallFailedException {
      ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      storeInSession(reportDto, executorToken, format, configArray);
   }

   /**
    * Prepares a report object and stores it in the session so it can be exported.
    * 
    * @param reportDto
    * @param outputFormat
    * @param outputFormat
    * @param mode
    * @throws ExpectedException
    */
   protected void storeInSession(ReportDto reportDto, String executorToken, String outputFormat,
         ReportExecutionConfig... reportExecutorConfigs) throws ExpectedException {
      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* get original report */
      Report orgReport = reportDtoService.getReport(reportDto);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      adjustedReport = referenceReport.createTemporaryVariant(adjustedReport);

      for (ReportExportViaSessionHook hooker : hookHandlerService.getHookers(ReportExportViaSessionHook.class)) {
         hooker.adjustReport(adjustedReport, reportExecutorConfigs);
      }

      ReportSessionCacheEntry entry = new ReportSessionCacheEntry();

      entry.setId(orgReport.getId());
      entry.setOutputFormat(outputFormat);
      entry.setAdjustedReport(adjustedReport);
      entry.setExecutorConfigs(reportExecutorConfigs);

      sessionCacheProvider.get().put(executorToken, entry);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void exportViaMail(@Named("report") ReportDto reportDto, String executorToken, final String format,
         List<ReportExecutionConfigDto> configs, String subject, String message, boolean compressed, List<StrippedDownUser> recipients)
         throws ServerCallFailedException, ExpectedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      for (ReportExportViaSessionHook hooker : hookHandlerService.getHookers(ReportExportViaSessionHook.class)) {
         hooker.adjustReport(toExecute, configArray);
      }

      CompiledReport cReport;
      try {
         cReport = reportExecutorService.execute(toExecute, format, configArray);

         SimpleMail mail = mailService.newSimpleMail();
         mail.setSubject(subject);
         
         String reportFileExtension = cReport.getFileExtension();
         
         if (compressed) {
            try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = cReport.getReport();
   
               try {
                  zipUtilsService.createZip(Collections
                        .singletonMap(toExecute.getName().replace(":", "_").replace("/", "_").replace("\\", "_").replace(" ", "_")
                              + "." + reportFileExtension, reportObj),
                        os);
               } catch (IOException e) {
                  throw new ServerCallFailedException(e);
               }
   
               SimpleAttachment attachement = new SimpleAttachment(os.toByteArray(), 
                     "application/zip", makeExportFilename(referenceReport) + ".zip"); 
               /* set content */
               mail.setContent(message, attachement);
            }
         } else {
            SimpleAttachment attachement = new SimpleAttachment(cReport.getReport(), cReport.getMimeType(),
                  makeExportFilename(referenceReport) + "." + cReport.getFileExtension() //$NON-NLS-1$
            );
            /* set content */
            mail.setContent(message, attachement);
         }
         
         mail.setToRecipients(recipients
               .stream()
               .map(sUser -> (User) userManagerService.getNodeById(sUser.getId()))
               .filter(user -> null != user.getEmail() && !"".equals(user.getEmail()))
               .map(User::getEmail)
               .collect(toList()));

         User currentUser = authenticatorServiceProvider.get().getCurrentUser();
         if (null != currentUser.getEmail() && !"".equals(currentUser.getEmail())) {
            InternetAddress mailFrom = new InternetAddress(currentUser.getEmail());
            mail.setFrom(mailFrom, true);
         }

         mailService.sendMail(mail);
      } catch (Exception e) {
         eventBus.fireEvent(new ExportReportViaMailFailedEvent(reportDto, executorToken, format, configs, subject,
               message, recipients));

         throw new ExpectedException("Could not export report via mail: " + e.getMessage(), e);
      }

   }

   private ReportExecutionConfig[] getConfigArray(String executorToken, List<ReportExecutionConfigDto> configs)
         throws ExpectedException {
      ReportExecutionConfig[] configArray = new ReportExecutionConfig[configs.size() + 1];
      for (int i = 0; i < configs.size(); i++)
         configArray[i] = (ReportExecutionConfig) dtoService.createPoso(configs.get(i));
      configArray[configs.size()] = new RECReportExecutorToken(executorToken);

      return configArray;
   }

   private String makeExportFilename(Report report) {
      String reportName = report.getName();
      if (report instanceof ReportVariant) {
         reportName = (((Report) report.getParent()).getName() + " - " + report.getName());
      }

      return fileNameService.sanitizeFileName(reportName);
   }

   @Override
   public String getExportDefaultSettingsAsXml(String identifier) {
      return configService.getConfigAsXmlFailsafe(configPath + identifier);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void exportSkipDownload(@Named("report") ReportDto reportDto, String executorToken, final String format)
         throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken,
            new ArrayList<ReportExecutionConfigDto>());

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      for (ReportExportViaSessionHook hooker : hookHandlerService.getHookers(ReportExportViaSessionHook.class)) {
         hooker.adjustReport(toExecute, configArray);
      }

      try {
         CompiledReport cReport = reportExecutorService.execute(toExecute, format, configArray);

      } catch (Exception e) {
         throw new ServerCallFailedException(e);
      }
   }

   @Override
   public String getExportDefaultCharset() {
      return reportServerService.getCharset();
   }

}
