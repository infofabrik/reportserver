package net.datenwerke.gf.server.upload;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.client.upload.rpc.FileUploadRpcService;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class FileUploadRpcServiceImpl extends SecuredRemoteServiceServlet implements FileUploadRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -7513824797769393953L;

   private final FileUploadService fileUploadService;

   @Inject
   public FileUploadRpcServiceImpl(FileUploadService fileUploadService) {
      this.fileUploadService = fileUploadService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public UploadResponse uploadInterimFile(FileToUpload file) throws ServerCallFailedException {
      /* prepare response */
      try {
         String contentType = fileUploadService.extractContentTypeFromHtml5Upload(file.getB64Data());
         byte[] data = fileUploadService.extractContentFromHtml5Upload(file.getB64Data());
         String name = file.getName();

         UploadedFile uf = new UploadedFile();
         uf.setContentType(contentType);
         uf.setFileName(name);
         uf.setFileBytes(data);

         UploadResponse response = fileUploadService.uploadInterimFile(uf);

         return response;
      } catch (IOException e) {
         throw new ServerCallFailedException(e);
      }
   }

}
