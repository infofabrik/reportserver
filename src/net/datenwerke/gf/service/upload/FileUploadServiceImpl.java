package net.datenwerke.gf.service.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

@Singleton
public class FileUploadServiceImpl implements FileUploadService {

   private final HookHandlerService hookHandlerService;
   private final TempFileService tempFileService;
   private final Provider<InterimUploadFileMap> interimMapProvider;

   @Inject
   public FileUploadServiceImpl(HookHandlerService hookHandlerService, TempFileService tempFileService,
         Provider<InterimUploadFileMap> interimMapProvider

   ) {
      this.hookHandlerService = hookHandlerService;
      this.tempFileService = tempFileService;
      this.interimMapProvider = interimMapProvider;
   }

   @Override
   public String uploadOccured(final UploadedFile uploadedFile) {

      final Optional<FileUploadHandlerHook> filtered = hookHandlerService.getHookers(FileUploadHandlerHook.class)
            .stream().filter(h -> h.consumes(uploadedFile.getHandler())).findAny();

      if (filtered.isPresent())
         return filtered.get().uploadOccured(uploadedFile);

      throw new IllegalStateException("No handler found for: " + uploadedFile.getHandler());
   }

   @Override
   public UploadResponse uploadInterimFile(UploadedFile file) throws IOException {
      Path tmpFile = tempFileService.createTempFile();
      Files.write(tmpFile, file.getFileBytes());

      file.setTmpLocation(tmpFile);
      file.clearData();

      /* obtain id and create response */
      String id = UUID.randomUUID().toString();
      interimMapProvider.get().put(id, file);

      UploadResponse response = new UploadResponse(id);
      response.setSuccess(true);
      response.setName(file.getFileName());
      response.setLength(file.getLength());

      return response;
   }

   @Override
   public String extractContentTypeFromHtml5Upload(String data) {
      int b64idx = data.indexOf("base64,");

      /* content type */
      String mimeType = (b64idx < 6) ? "" : data.substring(5, b64idx - 1);

      return mimeType;
   }

   @Override
   public byte[] extractContentFromHtml5Upload(String data) {
      int b64idx = data.indexOf("base64,");

      /* get data */
      String strData = data.substring(b64idx + 7);

      return Base64.decodeBase64(strData.getBytes());
   }

   @Override
   public UploadedFile extractUploadedFileFrom(SelectedFileWrapper file) {
      return extractUploadedFileFrom(file, true);
   }

   @Override
   public UploadedFile extractUploadedFileFrom(SelectedFileWrapper file, boolean remove) {
      ArrayList<SelectedFileWrapper> data = new ArrayList<SelectedFileWrapper>();
      data.add(file);

      List<UploadedFile> files = extractUploadedFilesFrom(data, remove);
      if (files.isEmpty())
         return null;
      return files.get(0);
   }

   @Override
   public List<UploadedFile> extractUploadedFilesFrom(List<SelectedFileWrapper> data) {
      return extractUploadedFilesFrom(data, true);
   }

   @Override
   public List<UploadedFile> extractUploadedFilesFrom(List<SelectedFileWrapper> data, boolean remove) {
      List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

      InterimUploadFileMap interimMap = interimMapProvider.get();

      for (SelectedFileWrapper file : data) {
         if (FileUploadUIModule.UPLOADED_FILE_TYPE.equals(file.getType())) {
            if (null != file.getOriginalDto())
               continue;

            UploadedFile uploadedFile;
            if (remove)
               uploadedFile = interimMap.remove(file.getId());
            else
               uploadedFile = interimMap.get(file.getId());

            if (null == uploadedFile)
               throw new IllegalStateException("Could not recover file: " + file.getId());

            UploadedFile copy = new UploadedFile(uploadedFile);
            try {
               byte[] filebytes = tempFileService.readTmpFileIntoByteArray(copy.getTmpLocation(), remove);
               copy.setFileBytes(filebytes);
            } catch (IOException e) {
               throw new IllegalStateException(e);
            }

            uploadedFiles.add(copy);
         }
      }

      return uploadedFiles;
   }

}
