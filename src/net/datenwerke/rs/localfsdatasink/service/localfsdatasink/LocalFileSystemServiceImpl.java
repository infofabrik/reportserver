package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class LocalFileSystemServiceImpl implements LocalFileSystemService {

   public static final int DEFAULT_BUFFER_SIZE = 8192;

   private static final String PROPERTY_LOCAL_FILE_SYSTEM_DISABLED = "localfilesystem[@disabled]";
   private static final String PROPERTY_LOCAL_FILE_SYSTEM_SCHEDULER_ENABLED = "localfilesystem[@supportsScheduling]";

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public LocalFileSystemServiceImpl(
		   Provider<ConfigService> configServiceProvider,
		   Provider<ReportService> reportServiceProvider
		   ) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
   }

   @Override
   public void sendToLocalFileSystem(Object report, LocalFileSystemDatasink localFileSystemDatasink, String filename,
         String folder) throws IOException {
      if (!isLocalFileSystemEnabled())
         throw new IllegalStateException("Local file system datasink is disabled");
      
      Objects.requireNonNull(localFileSystemDatasink, "LocalFileSystemDatasink is null!");
      Objects.requireNonNull(folder);
      Objects.requireNonNull(filename);

      final String path = Objects.requireNonNull(localFileSystemDatasink.getPath());

      if (null == path || path.trim().contentEquals(""))
         throw new IllegalArgumentException("Local file system is not configured correctly");

      Path baseDir = Paths.get(path);
      Path dir = Paths.get(path, folder);

      if (!Files.exists(baseDir))
         throw new IllegalArgumentException("Specified directory does not exist");

      if (!Files.isDirectory(baseDir))
         throw new IllegalArgumentException("You must specify a directory");

      if (!Files.isWritable(baseDir))
         throw new IllegalArgumentException("Cannot write into specified directory");

      if (!Files.exists(dir))
         Files.createDirectories(dir);

      try (InputStream is = reportServiceProvider.get().createInputStream(report)) {
         Path file = dir.resolve(filename);
         Files.copy(is, file, StandardCopyOption.REPLACE_EXISTING);
      }

   }

   @Override
   public Map<StorageType, Boolean> getLocalFileSystemEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.LOCALFILESYSTEM, isLocalFileSystemEnabled());
      configs.put(StorageType.LOCALFILESYSTEM_SCHEDULING, isLocalFileSystemSchedulingEnabled());
      return configs;
   }

   @Override
   public boolean isLocalFileSystemEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_LOCAL_FILE_SYSTEM_DISABLED,
            false);
   }

   @Override
   public boolean isLocalFileSystemSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE)
            .getBoolean(PROPERTY_LOCAL_FILE_SYSTEM_SCHEDULER_ENABLED, true);
   }

   @Override
   public void testLocalFileSystemDataSink(LocalFileSystemDatasink localFileSystemDatasink) throws IOException {
      if (!isLocalFileSystemEnabled())
         throw new IllegalStateException("Local file system datasink is disabled");

      sendToLocalFileSystem(
            "ReportServer Local Filesystem Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            localFileSystemDatasink, "reportserver-local-fs-test.txt",
            localFileSystemDatasink.getFolder());

   }

}