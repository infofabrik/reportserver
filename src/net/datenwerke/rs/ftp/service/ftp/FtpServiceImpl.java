package net.datenwerke.rs.ftp.service.ftp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class FtpServiceImpl implements FtpService {

   public static final String CONFIG_FILE = "datasinks/datasinks.cf";

   private static final String PROPERTY_FTP_DISABLED = "ftp[@disabled]";
   private static final String PROPERTY_FTP_SCHEDULER_ENABLED = "ftp[@supportsScheduling]";

   private static final String PROPERTY_SFTP_DISABLED = "sftp[@disabled]";
   private static final String PROPERTY_SFTP_SCHEDULER_ENABLED = "sftp[@supportsScheduling]";

   private final Provider<ConfigService> configServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public FtpServiceImpl(Provider<ConfigService> configServiceProvider) {
      this.configServiceProvider = configServiceProvider;
   }

   @Override
   public void sendToFtpServer(Object report, FtpDatasink ftpDatasink, String filename, String folder)
         throws IOException {
      doSendToFtpServer(false, report, ftpDatasink, filename, folder);
   }

   private ByteArrayInputStream createInputStream(Object report) {
      ByteArrayInputStream bis = null;
      if (report instanceof String)
         bis = new ByteArrayInputStream(((String) report).getBytes(StandardCharsets.UTF_8));
      else if (report instanceof byte[])
         bis = new ByteArrayInputStream(((byte[]) report));
      else
         throw new IllegalArgumentException("Report type not supported");

      return bis;
   }

   @Override
   public boolean isFtpEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTP_DISABLED, false);
   }

   @Override
   public boolean isFtpSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTP_SCHEDULER_ENABLED,
            false);
   }

   @Override
   public Map<StorageType, Boolean> getFtpEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.FTP, isFtpEnabled());
      configs.put(StorageType.FTP_SCHEDULING, isFtpSchedulingEnabled());
      return configs;
   }

   @Override
   public void sendToSftpServer(Object report, SftpDatasink sftpDatasink, String filename, String folder)
         throws IOException {
      doSendToFtpServer(true, report, sftpDatasink, filename, folder);
   }

   private void doSendToFtpServer(boolean sftp, Object report, BasicDatasink basicDatasink, String filename,
         String folder) throws IOException {

      if (null == basicDatasink)
         throw new IllegalArgumentException("basicDatasink is null!");

      final String host = basicDatasink.getHost();
      final int port = basicDatasink.getPort();
      final String username = basicDatasink.getUsername();

      if (null == host || host.trim().contentEquals("") || null == username || username.trim().contentEquals(""))
         throw new IllegalArgumentException((sftp ? "SFTP" : "FTP") + " server is not configured correctly");

      try (ByteArrayInputStream bis = createInputStream(report)) {

         DefaultFileSystemManager fsmanager = (DefaultFileSystemManager) VFS.getManager();

         FileSystemOptions opts = new FileSystemOptions();
         if (sftp) {
            SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
            Path knownHostsFile = Paths.get(configServiceProvider.get().getConfigFailsafe("security/misc.cf")
                  .getString("knownHosts", System.getProperty("user.home") + "/.ssh/known_hosts"));
            if (!Files.exists(knownHostsFile))
               throw new IllegalArgumentException("known_hosts file does not exist");

            SftpFileSystemConfigBuilder.getInstance().setKnownHosts(opts, knownHostsFile.toFile());
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "yes");
            SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
         } else
            FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

         StaticUserAuthenticator auth = new StaticUserAuthenticator(host, username, basicDatasink.getPassword());
         DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);

         String ftpurl = host + ":" + port + "/" + folder + "/" + filename;
         FileObject remoteFile = fsmanager.resolveFile(ftpurl, opts);

         try (OutputStream ostream = remoteFile.getContent().getOutputStream()) {
            IOUtils.copy(bis, ostream);
         }

         boolean success = remoteFile.exists();

         if (!success)
            throw new RuntimeException((sftp ? "SFTP" : "FTP") + " file was not uploaded successfully");
      }
   }

   @Override
   public boolean isSftpEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_SFTP_DISABLED, false);
   }

   @Override
   public boolean isSftpSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_SFTP_SCHEDULER_ENABLED,
            false);
   }

   @Override
   public Map<StorageType, Boolean> getSftpEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.SFTP, isSftpEnabled());
      configs.put(StorageType.SFTP_SCHEDULING, isSftpSchedulingEnabled());
      return configs;
   }

   @Override
   public void testFtpDataSink(FtpDatasink ftpDatasink) throws IOException {
      if (!isFtpEnabled())
         throw new IllegalStateException("ftp is disabled");

      sendToFtpServer("ReportServer FTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            ftpDatasink, "reportserver-ftp-test.txt", ftpDatasink.getFolder());
   }

   @Override
   public void testSftpDataSink(SftpDatasink sftpDatasink) throws IOException {
      if (!isSftpEnabled())
         throw new IllegalStateException("sftp is disabled");

      sendToSftpServer("ReportServer SFTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            sftpDatasink, "reportserver-sftp-test.txt", sftpDatasink.getFolder());
   }

}
