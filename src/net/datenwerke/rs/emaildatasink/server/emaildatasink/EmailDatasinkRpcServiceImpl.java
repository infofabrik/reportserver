package net.datenwerke.rs.emaildatasink.server.emaildatasink;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
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
import net.datenwerke.rs.emaildatasink.service.emaildatasink.configs.DatasinkEmailConfig;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionService;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.rs.utils.zip.ZipUtilsService.FileFilter;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
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
   private final ExceptionService exceptionServices;
   private final UserManagerService userManagerService;
   private final ZipUtilsService zipUtilsService;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;

   @Inject
   public EmailDatasinkRpcServiceImpl(
         ReportService reportService, 
         EmailDatasinkService emailDatasinkService,
         DtoService dtoService, 
         ReportExecutorService reportExecutorService, 
         ReportDtoService reportDtoService,
         SecurityService securityService, 
         HookHandlerService hookHandlerService, 
         ExceptionService exceptionServices,
         UserManagerService userManagerService, 
         ZipUtilsService zipUtilsService,
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider
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
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
   }

   @Override
   public void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String subject, String message,
         boolean compressed, List<StrippedDownUser> recipients) throws ServerCallFailedException {
      if (!(datasinkDto instanceof EmailDatasinkDto))
         throw new IllegalArgumentException("Not an email datasink");

      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      EmailDatasink emailDatasink = (EmailDatasink) dtoService.loadPoso(datasinkDto);

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
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(toExecute.getName() + "." + cReport.getFileExtension()), reportObj,
                     os);
               datasinkServiceProvider.get().exportIntoDatasink(os.toByteArray(),
                     emailDatasink,
                     new DatasinkEmailConfig() {

                        @Override
                        public String getFilename() {
                           return filename;
                        }

                        @Override
                        public boolean isSendSyncEmail() {
                           return true;
                        }

                        @Override
                        public String getSubject() {
                           return subject;
                        }

                        @Override
                        public List<User> getRecipients() {
                           return recipientUsers;
                        }

                        @Override
                        public String getBody() {
                           return message;
                        }
                     });
            }
         } else {
            String filename = name + "." + cReport.getFileExtension();
            datasinkServiceProvider.get().exportIntoDatasink(cReport.getReport(),
                  emailDatasink,
                  new DatasinkEmailConfig() {

                     @Override
                     public String getFilename() {
                        return filename;
                     }

                     @Override
                     public boolean isSendSyncEmail() {
                        return true;
                     }

                     @Override
                     public String getSubject() {
                        return subject;
                     }

                     @Override
                     public List<User> getRecipients() {
                        return recipientUsers;
                     }

                     @Override
                     public String getBody() {
                        return message;
                     }
                  });
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
      return datasinkServiceProvider.get().getEnabledConfigs(emailDatasinkService);
   }

   @Override
   public boolean testEmailDatasink(EmailDatasinkDto emailDatasinkDto) throws ServerCallFailedException {
      EmailDatasink emailDatasink = (EmailDatasink) dtoService.loadPoso(emailDatasinkDto);

      /* check rights */
      securityService.assertRights(emailDatasink, Read.class, Execute.class);

      try {
         String emailText = "ReportServer Email Datasink Test";
         datasinkServiceProvider.get().testDatasink(emailDatasink, new DatasinkEmailConfig() {

            @Override
            public String getFilename() {
               return "reportserver-email-datasink-test.txt";
            }

            @Override
            public boolean isSendSyncEmail() {
               return true;
            }

            @Override
            public String getSubject() {
               return emailText;
            }

            @Override
            public List<User> getRecipients() {
               return Arrays.asList(authenticatorServiceProvider.get().getCurrentUser());
            }

            @Override
            public String getBody() {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
               return emailText + " " + dateFormat.format(Calendar.getInstance().getTime());
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
            .getDefaultDatasink(emailDatasinkService);
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }

   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto,
         String filename, String folder, boolean compressed, String subject, String message,
         List<StrippedDownUser> recipients) throws ServerCallFailedException {

      /* check rights */
      securityService.assertRights(abstractNodeDto, Read.class);
      securityService.assertRights(datasinkDto, Read.class, Execute.class);
      DatasinkDefinition datasink = (DatasinkDefinition) dtoService.loadPoso(datasinkDto);
      if (abstractNodeDto instanceof FileServerFileDto) { // when a given object is of type file
         FileServerFile fileObj = (FileServerFile) dtoService.loadPoso(abstractNodeDto);
         String originalCompleteFilename = fileObj.getName();
         String originalFileExtension = "";
         String filenameWithoutExtension = originalCompleteFilename;
         if (originalCompleteFilename.contains(".")) {
            originalFileExtension = originalCompleteFilename.substring(originalCompleteFilename.lastIndexOf("."));
            filenameWithoutExtension = originalCompleteFilename.substring(0, originalCompleteFilename.lastIndexOf("."));
         }
         try {
            if (compressed) {
               String zipFilename = filename + ".zip";
               try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                  Object reportObj = fileObj.getData();
                  zipUtilsService.createZip(
                        zipUtilsService.cleanFilename(filenameWithoutExtension + originalFileExtension), reportObj, os);

                  exportIntoDatasink(emailDatasinkService, folder, datasink, zipFilename, os.toByteArray(), subject,
                        message, recipients);
               }
            } else {
               exportIntoDatasink(emailDatasinkService, folder, datasink, filename + originalFileExtension,
                     fileObj.getData(), subject, message, recipients);
            }
         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the file: " + e.getMessage(), e);
         }
      } else if (abstractNodeDto instanceof FileServerFolderDto) {
         FileServerFolder folderObj = (FileServerFolder) dtoService.loadPoso(abstractNodeDto);
         try {
            String zipFilename = filename + ".zip";
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               zipUtilsService.createZip(folderObj, os, new FileFilter() {
                  @Override
                  public boolean addNode(AbstractFileServerNode node) {
                     try {
                        return true;
                     } catch (Exception e) {
                     }
                     return false;
                  }
               });
               exportIntoDatasink(emailDatasinkService, folder, datasink, zipFilename, os.toByteArray(), subject,
                     message, recipients);
            }

         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the folder: " + e.getMessage(), e);
         }
      }

   }

   private void exportIntoDatasink(BasicDatasinkService basicDatasinkService, String folder,
         DatasinkDefinition datasink, String filename, byte[] os, String subject, String message,
         List<StrippedDownUser> recipients) throws DatasinkExportException {

      List<User> recipientUsers = recipients
            .stream()
            .map(sUser -> (User) userManagerService.getNodeById(sUser.getId()))
            .filter(user -> null != user.getEmail() && !"".equals(user.getEmail()))
            .collect(toList());
      datasinkServiceProvider.get().exportIntoDatasink(os, datasink,
            new DatasinkEmailConfig() {

         @Override
         public String getFilename() {
            return filename;
         }

         @Override
         public boolean isSendSyncEmail() {
            return true;
         }

         @Override
         public String getSubject() {
            return subject;
         }

         @Override
         public List<User> getRecipients() {
            return recipientUsers;
         }

         @Override
         public String getBody() {
            return message;
         }
      });
   }
}
