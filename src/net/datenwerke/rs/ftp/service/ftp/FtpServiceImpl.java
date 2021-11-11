package net.datenwerke.rs.ftp.service.ftp;

import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_FTPS_DISABLED;
import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_FTPS_SCHEDULING_ENABLED;
import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_FTP_DISABLED;
import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_FTP_SCHEDULING_ENABLED;
import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_SFTP_DISABLED;
import static net.datenwerke.rs.ftp.service.ftp.FtpModule.PROPERTY_SFTP_SCHEDULING_ENABLED;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultSftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class FtpServiceImpl implements FtpService {

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;
   
   private final Provider<Optional<FtpDatasink>> defaultFtpDatasinkProvider;
   private final Provider<Optional<SftpDatasink>> defaultSftpDatasinkProvider;
   private final Provider<Optional<FtpsDatasink>> defaultFtpsDatasinkProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final static String TLS_PROPERTY = "jdk.tls.client.protocols";

   @Inject
   public FtpServiceImpl(
		   Provider<ConfigService> configServiceProvider,
		   Provider<ReportService> reportServiceProvider,
		   Provider<DatasinkService> datasinkServiceProvider,
		   @DefaultFtpDatasink Provider<Optional<FtpDatasink>> defaultFtpDatasinkProvider,
		   @DefaultSftpDatasink Provider<Optional<SftpDatasink>> defaultSftpDatasinkProvider,
		   @DefaultFtpsDatasink Provider<Optional<FtpsDatasink>> defaultFtpsDatasinkProvider
		   ) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.defaultFtpDatasinkProvider = defaultFtpDatasinkProvider;
      this.defaultSftpDatasinkProvider = defaultSftpDatasinkProvider;
      this.defaultFtpsDatasinkProvider = defaultFtpsDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoFtp(Object report, FtpDatasink ftpDatasink, String filename, String folder)
         throws IOException {
      if (!isFtpEnabled())
         throw new IllegalStateException("ftp is disabled");
      
      doSendToFtpServer(StorageType.FTP, report, ftpDatasink, filename, folder);
   }

   @Override
   public boolean isFtpEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_FTP_DISABLED);
   }

   @Override
   public boolean isFtpSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_FTP_SCHEDULING_ENABLED);
   }

   @Override
   public Map<StorageType, Boolean> getFtpEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.FTP, PROPERTY_FTP_DISABLED,
            StorageType.FTP_SCHEDULING, PROPERTY_FTP_SCHEDULING_ENABLED);
   }

   @Override
   public void exportIntoSftp(Object report, SftpDatasink sftpDatasink, String filename, String folder)
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
      return datasinkServiceProvider.get().isEnabled(PROPERTY_SFTP_DISABLED);
   }

   @Override
   public boolean isSftpSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_SFTP_SCHEDULING_ENABLED);
   }

   @Override
   public Map<StorageType, Boolean> getSftpEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.SFTP, PROPERTY_SFTP_DISABLED,
            StorageType.SFTP_SCHEDULING, PROPERTY_SFTP_SCHEDULING_ENABLED);
   }

   @Override
   public void testFtpDataSink(FtpDatasink ftpDatasink) throws IOException {
      if (!isFtpEnabled())
         throw new IllegalStateException("ftp is disabled");

      exportIntoFtp("ReportServer FTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            ftpDatasink, "reportserver-ftp-test.txt", ftpDatasink.getFolder());
   }

   @Override
   public void testSftpDataSink(SftpDatasink sftpDatasink) throws IOException {
      if (!isSftpEnabled())
         throw new IllegalStateException("sftp is disabled");

      exportIntoSftp("ReportServer SFTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            sftpDatasink, "reportserver-sftp-test.txt", sftpDatasink.getFolder());
   }
   
   @Override
   public void testFtpsDataSink(FtpsDatasink ftpsDatasink) throws IOException {
       if (!isFtpsEnabled())
           throw new IllegalStateException("ftps is disabled");

       exportIntoFtps("ReportServer FTPS Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
               ftpsDatasink, "reportserver-ftps-test.txt", ftpsDatasink.getFolder());

   }

   @Override
   public void exportIntoFtps(Object report, FtpsDatasink ftpsDatasink, String filename, String folder)
           throws IOException {
      if (!isFtpsEnabled())
         throw new IllegalStateException("ftps is disabled");
      
      doSendToFtpServer(StorageType.FTPS, report, ftpsDatasink, filename, folder);
   }

   @Override
   public boolean isFtpsEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_FTPS_DISABLED);
   }

   @Override
   public Map<StorageType, Boolean> getFtpsEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.FTPS, PROPERTY_FTPS_DISABLED,
            StorageType.FTPS_SCHEDULING, PROPERTY_FTPS_SCHEDULING_ENABLED);
   }

   @Override
   public boolean isFtpsSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_FTPS_SCHEDULING_ENABLED);
   }

   @Override
   public Optional<FtpDatasink> getDefaultFtpDatasink() {
      return defaultFtpDatasinkProvider.get();
   }

   @Override
   public Optional<SftpDatasink> getDefaultSftpDatasink() {
      return defaultSftpDatasinkProvider.get();
   }

   @Override
   public Optional<FtpsDatasink> getDefaultFtpsDatasink() {
      return defaultFtpsDatasinkProvider.get();
   }

}
