package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import static net.datenwerke.rs.localfsdatasink.service.localfsdatasink.LocalFileSystemModule.PROPERTY_LOCAL_FILESYSTEM_DISABLED;
import static net.datenwerke.rs.localfsdatasink.service.localfsdatasink.LocalFileSystemModule.PROPERTY_LOCAL_FILESYSTEM_SCHEDULING_ENABLED;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.annotations.DefaultLocalFileSystemDatasink;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class LocalFileSystemServiceImpl implements LocalFileSystemService {

   public static final int DEFAULT_BUFFER_SIZE = 8192;

   private final Provider<ReportService> reportServiceProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final Provider<Optional<LocalFileSystemDatasink>> defaultDatasinkProvider;

   @Inject
   public LocalFileSystemServiceImpl(
		   Provider<ReportService> reportServiceProvider,
		   Provider<DatasinkService> datasinkServiceProvider,
		   @DefaultLocalFileSystemDatasink Provider<Optional<LocalFileSystemDatasink>> defaultDatasinkProvider
		   ) {
      this.reportServiceProvider = reportServiceProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
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
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.LOCALFILESYSTEM, PROPERTY_LOCAL_FILESYSTEM_DISABLED,
            StorageType.LOCALFILESYSTEM_SCHEDULING, PROPERTY_LOCAL_FILESYSTEM_SCHEDULING_ENABLED);
   }

   @Override
   public boolean isLocalFileSystemEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_LOCAL_FILESYSTEM_DISABLED);
   }

   @Override
   public boolean isLocalFileSystemSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_LOCAL_FILESYSTEM_SCHEDULING_ENABLED);
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
   
   @Override
   public Optional<LocalFileSystemDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

}