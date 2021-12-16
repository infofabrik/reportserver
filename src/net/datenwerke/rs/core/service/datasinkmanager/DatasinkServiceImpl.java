package net.datenwerke.rs.core.service.datasinkmanager;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.rs.utils.zip.ZipUtilsService.FileFilter;

public class DatasinkServiceImpl implements DatasinkService {

   private final Provider<ConfigService> configServiceProvider;
   private final DtoService dtoService;
   private final ZipUtilsService zipUtilsService;
   
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public DatasinkServiceImpl(Provider<ConfigService> configServiceProvider, DtoService dtoService, ZipUtilsService zipUtilsService) {
      this.configServiceProvider = configServiceProvider;
      this.dtoService = dtoService;
      this.zipUtilsService = zipUtilsService;
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
   public void testDatasink(DatasinkDefinition datasinkDefinition, BasicDatasinkService basicDatasinkService,
         DatasinkConfiguration config) throws DatasinkExportException {
      if (!isEnabled(basicDatasinkService))
         throw new IllegalStateException("datasink is disabled: " + basicDatasinkService);
      exportIntoDatasink(
            "ReportServer " + basicDatasinkService.getDatasinkPropertyName() + " Datasink Test "
                  + dateFormat.format(Calendar.getInstance().getTime()),
            datasinkDefinition, basicDatasinkService, config);
   }

   @Override
   public void exportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition,
         BasicDatasinkService basicDatasinkService, DatasinkConfiguration config) throws DatasinkExportException {
      basicDatasinkService.doExportIntoDatasink(report, datasinkDefinition, config);
   }

   @Override
   public Optional<? extends DatasinkDefinition> getDefaultDatasink(BasicDatasinkService basicDatasinkService) {
      return basicDatasinkService.getDefaultDatasink();
   }
   
   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto fileDto, DatasinkDefinitionDto datasinkDto,
         BasicDatasinkService basicDatasinkService, String filename, String folder, boolean compressed)
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

                  exportIntoDatasink(basicDatasinkService, folder, datasink, zipFilename, os.toByteArray());
               }
            } else {
               exportIntoDatasink(basicDatasinkService, folder, datasink, filename + originalFileExtension,
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
               exportIntoDatasink(basicDatasinkService, folder, datasink, zipFilename, os.toByteArray());
            }

         } catch (Exception e) {
            throw new ServerCallFailedException("Could not send the file: " + e.getMessage(), e);
         }
      }
   }

   private void exportIntoDatasink(BasicDatasinkService basicDatasinkService, String folder,
         DatasinkDefinition datasink, String filename, byte[] os) throws DatasinkExportException {
      exportIntoDatasink(os, datasink, basicDatasinkService, new DatasinkFilenameFolderConfig() {

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
