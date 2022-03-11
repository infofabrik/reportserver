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
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.rs.utils.zip.ZipUtilsService.FileFilter;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class DatasinkServiceImpl implements DatasinkService {

   private final Provider<ConfigService> configServiceProvider;
   private final DtoService dtoService;
   private final ZipUtilsService zipUtilsService;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;

   @Inject
   public DatasinkServiceImpl(
         Provider<ConfigService> configServiceProvider, DtoService dtoService,
         ZipUtilsService zipUtilsService,
         Provider<AuthenticatorService> authenticatorServiceProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.dtoService = dtoService;
      this.zipUtilsService = zipUtilsService;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
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
   public void exportIntoDatasink(Object report, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(report, user, datasinkDefinition, config);
   }
   
   @Override
   public void exportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(report, authenticatorServiceProvider.get().getCurrentUser(), 
            datasinkDefinition, config);
   }
   
   @Override
   public void exportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition) throws DatasinkExportException {
      datasinkDefinition.getDatasinkService().doExportIntoDatasink(report, authenticatorServiceProvider.get().getCurrentUser(), 
            datasinkDefinition, datasinkDefinition.getDefaultConfiguration());
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
            throw new ServerCallFailedException("Could not send the folder: " + e.getMessage(), e);
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
            throw new ServerCallFailedException("Could not send the file: " + e.getMessage(), e);
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

}
