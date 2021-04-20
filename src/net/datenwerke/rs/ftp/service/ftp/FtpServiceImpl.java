package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftps.FtpsDataChannelProtectionLevel;
import org.apache.commons.vfs2.provider.ftps.FtpsFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.BytesIdentityInfo;
import org.apache.commons.vfs2.provider.sftp.IdentityProvider;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasink;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class FtpServiceImpl implements FtpService {

   public static final String CONFIG_FILE = "datasinks/datasinks.cf";

   private static final String PROPERTY_FTP_DISABLED = "ftp[@disabled]";
   private static final String PROPERTY_FTP_SCHEDULER_ENABLED = "ftp[@supportsScheduling]";

   private static final String PROPERTY_SFTP_DISABLED = "sftp[@disabled]";
   private static final String PROPERTY_SFTP_SCHEDULER_ENABLED = "sftp[@supportsScheduling]";
   
   private static final String PROPERTY_FTPS_DISABLED = "ftps[@disabled]";
   private static final String PROPERTY_FTPS_SCHEDULER_ENABLED = "ftps[@supportsScheduling]";

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final static String TLS_PROPERTY = "jdk.tls.client.protocols";

   @Inject
   public FtpServiceImpl(
		   Provider<ConfigService> configServiceProvider,
		   Provider<ReportService> reportServiceProvider
		   ) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
   }

   @Override
   public void sendToFtpServer(Object report, FtpDatasink ftpDatasink, String filename, String folder)
         throws IOException {
      if (!isFtpEnabled())
         throw new IllegalStateException("ftp is disabled");
      
      doSendToFtpServer(StorageType.FTP, report, ftpDatasink, filename, folder);
   }

   @Override
   public boolean isFtpEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTP_DISABLED, false);
   }

   @Override
   public boolean isFtpSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTP_SCHEDULER_ENABLED,
            true);
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
      if (!isSftpEnabled())
         throw new IllegalStateException("sftp is disabled");
      
      doSendToFtpServer(StorageType.SFTP, report, sftpDatasink, filename, folder);
   }

   private void doSendToFtpServer(StorageType storageType, Object report, BasicDatasink basicDatasink, String filename,
         String folder) throws IOException {

      Objects.requireNonNull(basicDatasink, "datasink is null!");
      Objects.requireNonNull(folder);
      Objects.requireNonNull(filename);
      
      if (storageType != StorageType.FTP && storageType != StorageType.SFTP && storageType != StorageType.FTPS)
         throw new IllegalArgumentException("storage type not allowed");
      
      String currentTlsProperty = System.getProperty(TLS_PROPERTY);

      final String host = Objects.requireNonNull(basicDatasink.getHost());
      final int port = basicDatasink.getPort();
      final String username = basicDatasink.getUsername();

      if (null == host || host.trim().contentEquals("") || null == username || username.trim().contentEquals(""))
         throw new IllegalArgumentException(storageType.name() + " server is not configured correctly");

      try (InputStream bis = reportServiceProvider.get().createInputStream(report)) {

         DefaultFileSystemManager fsmanager = (DefaultFileSystemManager) VFS.getManager();

         FileSystemOptions opts = new FileSystemOptions();
         switch (storageType) {
         case FTP:
            configureFtp((FtpDatasink) basicDatasink, opts, host, port, username);
            break;
         case SFTP:
            configureSftp((SftpDatasink) basicDatasink, opts, host, port, username);
            break;
         case FTPS:
            configureFtps((FtpsDatasink) basicDatasink, opts, host, port, username);
            break;
         default:
            break;
         }

         String ftpurl = host + ":" + port + "/" + folder + "/" + filename;
         FileObject remoteFile = fsmanager.resolveFile(ftpurl, opts);

         try (OutputStream ostream = remoteFile.getContent().getOutputStream()) {
            IOUtils.copy(bis, ostream);
         }

         if (!remoteFile.exists())
            throw new RuntimeException(storageType.name() + " file was not uploaded successfully");
      } finally {
         // we return to default
         if (storageType == StorageType.FTPS) {
            if (null != currentTlsProperty)
               System.setProperty(TLS_PROPERTY, currentTlsProperty);
            else
               System.clearProperty(TLS_PROPERTY);
         }
      }
   }

   private void configureFtp(FtpDatasink ftpDatasink, FileSystemOptions opts, String host, int port, String username)
         throws FileSystemException {
      Objects.requireNonNull(ftpDatasink.getFtpMode());
      
      FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

      StaticUserAuthenticator auth = new StaticUserAuthenticator(host, username,
            ftpDatasink.getPassword());
      DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
      
      if (FtpDatasinkConfigProviderHooker.ACTIVE_MODE.equals(ftpDatasink.getFtpMode()))
         FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, false);
      else if (FtpDatasinkConfigProviderHooker.PASSIVE_MODE.equals(ftpDatasink.getFtpMode()))
         FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
      
   }
   
   private void configureSftp(SftpDatasink sftpDatasink, FileSystemOptions opts, String host, int port, String username)
         throws FileSystemException {
      Path knownHostsFile = Paths.get(configServiceProvider.get().getConfigFailsafe("security/misc.cf")
            .getString("knownHosts", System.getProperty("user.home") + "/.ssh/known_hosts"));
      if (!Files.exists(knownHostsFile))
         throw new IllegalArgumentException("known_hosts file does not exist");
      
      Objects.requireNonNull(sftpDatasink.getAuthenticationType());

      SftpFileSystemConfigBuilder.getInstance().setKnownHosts(opts, knownHostsFile.toFile());
      SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "yes");
      SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

      /*
       * Even if we use public key authentication the user must be set. In case of
       * public key authentication, the password will be null.
       */
      StaticUserAuthenticator auth = new StaticUserAuthenticator(host, username, sftpDatasink.getPassword());
      DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);

      if (SftpPublicKeyAuthenticatorHooker.AUTHENTICATION_TYPE.equals(sftpDatasink.getAuthenticationType())) {
         IdentityProvider idProvider = new BytesIdentityInfo(sftpDatasink.getPrivateKey(),
               null != sftpDatasink.getPrivateKeyPassphrase() ? sftpDatasink.getPrivateKeyPassphrase().getBytes()
                     : null);
         SftpFileSystemConfigBuilder.getInstance().setIdentityProvider(opts, idProvider);
      }

   }
   
   private void configureFtps(FtpsDatasink ftpsDatasink, FileSystemOptions opts, String host, int port, String username)
         throws FileSystemException {
      Objects.requireNonNull(ftpsDatasink.getDataChannelProtectionLevel());
      Objects.requireNonNull(ftpsDatasink.getFtpMode());

      // TLSv1.3 not working currently
      // https://stackoverflow.com/questions/53913758/java-11-https-connection-fails-with-ssl-handshakeexception-while-using-jsoup
      // https://stackoverflow.com/questions/57601284/java-11-and-12-ssl-sockets-fail-on-a-handshake-failure-error-with-tlsv1-3-enable
      System.setProperty(TLS_PROPERTY, "TLSv1.1,TLSv1.2");

      FtpsFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

      StaticUserAuthenticator auth = new StaticUserAuthenticator(host, username, ftpsDatasink.getPassword());
      DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);

      FtpsFileSystemConfigBuilder.getInstance().setDataChannelProtectionLevel(opts,
            FtpsDataChannelProtectionLevel.valueOf(ftpsDatasink.getDataChannelProtectionLevel()));

      if (FtpDatasinkConfigProviderHooker.ACTIVE_MODE.equals(ftpsDatasink.getFtpMode()))
         FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, false);
      else if (FtpDatasinkConfigProviderHooker.PASSIVE_MODE.equals(ftpsDatasink.getFtpMode()))
         FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);

   }

   @Override
   public boolean isSftpEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_SFTP_DISABLED, false);
   }

   @Override
   public boolean isSftpSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_SFTP_SCHEDULER_ENABLED,
            true);
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
   
   @Override
   public void testFtpsDataSink(FtpsDatasink ftpsDatasink) throws IOException {
       if (!isFtpsEnabled())
           throw new IllegalStateException("ftps is disabled");

       sendToFtpsServer("ReportServer FTPS Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
               ftpsDatasink, "reportserver-ftps-test.txt", ftpsDatasink.getFolder());

   }

   @Override
   public void sendToFtpsServer(Object report, FtpsDatasink ftpsDatasink, String filename, String folder)
           throws IOException {
      if (!isFtpsEnabled())
         throw new IllegalStateException("ftps is disabled");
      
      doSendToFtpServer(StorageType.FTPS, report, ftpsDatasink, filename, folder);
   }

   @Override
   public boolean isFtpsEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTPS_DISABLED, false);
   }

   @Override
   public Map<StorageType, Boolean> getFtpsEnabledConfigs() {
       Map<StorageType, Boolean> configs = new HashMap<>();
       configs.put(StorageType.FTPS, isFtpsEnabled());
       configs.put(StorageType.FTPS_SCHEDULING, isFtpsSchedulingEnabled());
       return configs;
   }

   @Override
   public boolean isFtpsSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_FTPS_SCHEDULER_ENABLED,
            true);
   }

}
