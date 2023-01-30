package net.datenwerke.rs.core.service.datasinkmanager;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkDispatchNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.rs.utils.zip.ZipUtilsService.FileFilter;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class DatasinkServiceImpl implements DatasinkService {

   private final Provider<ConfigService> configServiceProvider;
   private final DtoService dtoService;
   private final ZipUtilsService zipUtilsService;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;

   @Inject
   public DatasinkServiceImpl(
         Provider<ConfigService> configServiceProvider, DtoService dtoService,
         ZipUtilsService zipUtilsService,
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<HookHandlerService> hookHandlerServiceProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.dtoService = dtoService;
      this.zipUtilsService = zipUtilsService;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
   }

   @Override
   public String getDefaultFolder(FolderedDatasink datasink) {
      return datasink.getFolder();
   }

   @Override
   public boolean isEnabled(BasicDatasinkService datasinkService) {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE)
            .getBoolean(datasinkService.getDatasinkPropertyName() + PROPERTY_DEFAULT_DISABLED, false);
   }

   @Override
   public boolean isSchedulingEnabled(BasicDatasinkService datasinkService) {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE)
            .getBoolean(datasinkService.getDatasinkPropertyName() + PROPERTY_DEFAULT_SCHEDULING_ENABLED, true);
   }

   @Override
   public Map<StorageType, Boolean> getEnabledConfigs(BasicDatasinkService datasinkService) {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(datasinkService.getStorageType(), isEnabled(datasinkService));
      configs.put(datasinkService.getSchedulingStorageType(), isSchedulingEnabled(datasinkService));
      return configs;
   }

   @Override
   public void testDatasink(DatasinkDefinition datasinkDefinition, DatasinkConfiguration config) 
         throws DatasinkExportException {
      BasicDatasinkService datasinkService = datasinkDefinition.getDatasinkService();
      if (!isEnabled(datasinkService))
         throw new IllegalStateException("datasink is disabled: " + datasinkService);
      exportIntoDatasink(
            "ReportServer " + datasinkService.getDatasinkPropertyName() + " Datasink Test "
                  + DateUtils.formatCurrentDate(),
            datasinkDefinition, config);
   }

   @Override
   public void exportIntoDatasink(Object data, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(data, user, datasinkDefinition, config);
   }
   
   @Override
   public void exportIntoDatasink(Object data, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(data, authenticatorServiceProvider.get().getCurrentUser(), 
            datasinkDefinition, config);
   }
   
   @Override
   public void exportIntoDatasink(Object data, DatasinkDefinition datasinkDefinition, String fileEnding)
         throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(data, authenticatorServiceProvider.get().getCurrentUser(), 
            datasinkDefinition, datasinkDefinition.getDefaultConfiguration(fileEnding));
   }

   @Override
   public Optional<? extends DatasinkDefinition> getDefaultDatasink(BasicDatasinkService basicDatasinkService) {
      return basicDatasinkService.getDefaultDatasink();
   }

   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto fileDto, DatasinkDefinitionDto datasinkDto,
         String filename, String folder, boolean compressed)
         throws ServerCallFailedException {
      DatasinkDefinition datasink = (DatasinkDefinition) dtoService.loadPoso(datasinkDto);
      if (fileDto instanceof FileServerFileDto) { // when a given object is of type file
         FileServerFile fileObj = (FileServerFile) dtoService.loadPoso(fileDto);
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

                  exportIntoDatasink(folder, datasink, zipFilename, os.toByteArray());
               }
            } else {
               exportIntoDatasink(folder, datasink, filename + originalFileExtension,
                     fileObj.getData());
            }
         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the file: " + e.getMessage(), e);
         }
      } else if (fileDto instanceof FileServerFolderDto) {
         FileServerFolder folderObj = (FileServerFolder) dtoService.loadPoso(fileDto);
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
               exportIntoDatasink(folder, datasink, zipFilename, os.toByteArray());
            }

         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the folder: " + e.getMessage(), e);
         }
      }
   }

   private void exportIntoDatasink(String folder,
         DatasinkDefinition datasink, String filename, byte[] os) throws DatasinkExportException {
      exportIntoDatasink(os, authenticatorServiceProvider.get().getCurrentUser(), 
            datasink, new DatasinkFilenameFolderConfig() {

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

   @Override
   public void exportIntoDatasink(final ReportExecuteJob rJob, final boolean compress, final DatasinkDefinition datasink,
         final DatasinkConfiguration config) throws ActionExecutionException {
      try {
         if (compress) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = rJob.getExecutedReport().getReport();
               String reportFileExtension = rJob.getExecutedReport().getFileExtension();
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(rJob.getReport().getName() + "." + reportFileExtension), reportObj,
                     os);
               final byte[] report = os.toByteArray();
               exportIntoDatasink(report, rJob.getExecutor(), datasink, config);
               
               hookHandlerServiceProvider.get().getHookers(DatasinkDispatchNotificationHook.class)
                     .forEach(hooker -> hooker.notifyOfScheduledReportDispatched(report, rJob, datasink, config));
            }
         } else {
            exportIntoDatasink(rJob.getExecutedReport().getReport(), rJob.getExecutor(), datasink, config);
            
            hookHandlerServiceProvider.get().getHookers(DatasinkDispatchNotificationHook.class).forEach(hooker -> hooker
                  .notifyOfScheduledReportDispatched(rJob.getExecutedReport().getReport(), rJob, datasink, config));
         }
      } catch (Exception e) {
         throw new ActionExecutionException("Report could not be sent to datasink: " + datasink.getClass().getSimpleName(), e);
      }
   }
   
   @Override
   public void exportIntoDatasink(CompiledReport cReport, String name, boolean compress, DatasinkDefinition datasink,
         DatasinkConfiguration config) throws DatasinkExportException {
      try {
         if (compress) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = cReport.getReport();
               zipUtilsService.createZip(zipUtilsService.cleanFilename(name + "." + cReport.getFileExtension()),
                     reportObj, os);
               final byte[] report = os.toByteArray();
               exportIntoDatasink(report, datasink, config);
               hookHandlerServiceProvider.get().getHookers(DatasinkDispatchNotificationHook.class)
                     .forEach(hooker -> hooker.notifyOfCompiledReportDispatched(report, datasink, config));
            }
         } else {
            exportIntoDatasink(cReport.getReport(), datasink, config);
            hookHandlerServiceProvider.get().getHookers(DatasinkDispatchNotificationHook.class)
                  .forEach(hooker -> hooker.notifyOfCompiledReportDispatched(cReport.getReport(), datasink, config));
         }
      } catch (Exception e) {
         throw new DatasinkExportException(
               "Report could not be sent to datasink: " + datasink.getClass().getSimpleName(), e);
      }
   }

   @Override
   public String getFilenameForDatasink(ReportExecuteJob rJob, boolean compress, String filename) {
      if (compress)
         return filename + ".zip";
      else 
         return filename + "." + rJob.getExecutedReport().getFileExtension();
   }

   @Override
   public String getFilenameForDatasink(String name, CompiledReport cReport, boolean compress) {
      if (compress) 
         return name + ".zip";
      else 
         return name + "." + cReport.getFileExtension();
   }

}
