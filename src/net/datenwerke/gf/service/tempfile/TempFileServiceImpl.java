package net.datenwerke.gf.service.tempfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Provider;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.gf.service.tempfile.annotations.TempDirLocation;
import net.datenwerke.security.service.usermanager.entities.User;

@Singleton
public class TempFileServiceImpl implements TempFileService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private Provider<String> tempDir;
   private Map<String, TempFile> webAccessibleTempFiles = Collections.synchronizedMap(new HashMap<>());

   @Inject
   public TempFileServiceImpl(@TempDirLocation Provider<String> tempDir) {
      this.tempDir = tempDir;

   }

   @Override
   public synchronized Path createTempFile() throws IOException {
      Path dir = Paths.get(tempDir.get());
      if (!Files.exists(dir) || !Files.isDirectory(dir)) {
         logger.warn("cannot access tempdir: " + tempDir);
         throw new IllegalStateException("cannot access tempdir: " + tempDir);
      }

      return Files.createTempFile(dir, "rs-tmp-", null);
   }

   @Override
   public TempFile createWebAccessibleTempFile(String id, User... permittedUsers) throws IOException {
      TempFile tmpFile = new TempFile(createTempFile(), id, Arrays.asList(permittedUsers));
      webAccessibleTempFiles.put(tmpFile.getWebId(), tmpFile);

      return tmpFile;
   }

   @Override
   public TempFile createWebAccessibleTempFile(User... permittedUsers) throws IOException {
      return createWebAccessibleTempFile(UUID.randomUUID().toString(), permittedUsers);
   }

   @Override
   public TempFile getTempFileById(String id) {
      TempFile tempFile = webAccessibleTempFiles.get(id);

      if (null == tempFile)
         return null;

      tempFile.setLastAccessed();

      return tempFile;
   }

   @Override
   public byte[] readTmpFileIntoByteArray(Path path) throws IOException {
      return readTmpFileIntoByteArray(path, true);
   }

   @Override
   public byte[] readTmpFileIntoByteArray(Path file, boolean removeFile) throws IOException {
      /* ensure file is in right location */
      if (!isTmpFile(file))
         throw new IllegalArgumentException("Tried to read file: " + file);

      byte[] fileContents = Files.readAllBytes(file);

      /* remove? */
      if (removeFile)
         Files.delete(file);

      return fileContents;
   }

   @Override
   public boolean isTmpFile(Path path) {
      Path dir = Paths.get(tempDir.get());
      if (!Files.exists(dir) || !Files.isDirectory(dir)) {
         logger.warn("cannot access tempdir: " + tempDir);
         throw new IllegalStateException("cannot access tempdir: " + tempDir);
      }

      return path.startsWith(dir);
   }

}
