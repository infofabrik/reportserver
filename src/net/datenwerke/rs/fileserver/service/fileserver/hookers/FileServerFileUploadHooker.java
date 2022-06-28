package net.datenwerke.rs.fileserver.service.fileserver.hookers;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class FileServerFileUploadHooker implements FileUploadHandlerHook {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<FileServerService> fileServiceProvider;

   @Inject
   public FileServerFileUploadHooker(Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<SecurityService> securityServiceProvider, Provider<FileServerService> fileServiceProvider) {
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.fileServiceProvider = fileServiceProvider;
   }

   @Override
   public boolean consumes(String handler) {
      return FileServerUiModule.FILE_UPLOAD_HANDLER_ID.equals(handler);
   }

   @Override
   public String uploadOccured(UploadedFile uploadedFile, Map<String,String> context) {
      Map<String, String> metadataMap = uploadedFile.getMetadata();

      long fileId = Long.valueOf(metadataMap.get(FileServerUiModule.UPLOAD_FILE_ID_FIELD));
      String fileName = uploadedFile.getFileName();
      byte[] fileContents = uploadedFile.getFileBytes();

      if (null == fileName || null == fileContents || fileContents.length == 0 || fileName.isEmpty())
         return null;

      SecurityService securityService = securityServiceProvider.get();
      securityService.assertUserLoggedIn();

      FileServerService service = fileServiceProvider.get();
      AbstractFileServerNode fileNode = service.getNodeById(fileId);

      securityService.assertActions(fileNode, UpdateAction.class);

      if (fileNode instanceof FileServerFile) {
         FileServerFile file = (FileServerFile) fileNode;

         file.setContentType(uploadedFile.getContentType());
         file.setData(fileContents);
         if (null == file.getName() || "".equals(file.getName()))
            file.setName(fileName);

         service.merge(file);
      }

      return null;
   }

}
