package net.datenwerke.rs.ftp.service.ftp;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_KEY;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultSftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public class FtpModule extends AbstractModule {

   // ftp
   private static final String PROPERTY_FTP_DATASINK = "ftp";
   public static final String PROPERTY_DEFAULT_FTP_DATASINK_ID = PROPERTY_FTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_FTP_DATASINK_NAME = PROPERTY_FTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_FTP_DATASINK_KEY = PROPERTY_FTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_FTP_DISABLED = PROPERTY_FTP_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_FTP_SCHEDULING_ENABLED = PROPERTY_FTP_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   // sftp
   private static final String PROPERTY_SFTP_DATASINK = "sftp";
   public static final String PROPERTY_DEFAULT_SFTP_DATASINK_ID = PROPERTY_SFTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_SFTP_DATASINK_NAME = PROPERTY_SFTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_SFTP_DATASINK_KEY = PROPERTY_SFTP_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_SFTP_DISABLED = PROPERTY_SFTP_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_SFTP_SCHEDULING_ENABLED = PROPERTY_SFTP_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   // ftps
   private static final String PROPERTY_FTPS_DATASINK = "ftps";
   public static final String PROPERTY_DEFAULT_FTPS_DATASINK_ID = PROPERTY_FTPS_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_FTPS_DATASINK_NAME = PROPERTY_FTPS_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_FTPS_DATASINK_KEY = PROPERTY_FTPS_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_FTPS_DISABLED = PROPERTY_FTPS_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_FTPS_SCHEDULING_ENABLED = PROPERTY_FTPS_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      bind(FtpService.class).to(FtpServiceImpl.class);
      bind(SftpService.class).to(SftpServiceImpl.class);
      bind(FtpsService.class).to(FtpsServiceImpl.class);
      bind(FtpSenderService.class).to(FtpSenderServiceImpl.class);

      requestStaticInjection(FtpDatasink.class);
      requestStaticInjection(SftpDatasink.class);
      requestStaticInjection(FtpsDatasink.class);

      bind(FtpStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultFtpDatasink
   public Optional<FtpDatasink> provideDefaultFtpDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(FtpDatasink.class, PROPERTY_DEFAULT_FTP_DATASINK_ID,
            PROPERTY_DEFAULT_FTP_DATASINK_NAME, PROPERTY_DEFAULT_FTP_DATASINK_KEY);
   }

   @Provides
   @Inject
   @DefaultSftpDatasink
   public Optional<SftpDatasink> provideDefaultSftpDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(SftpDatasink.class, PROPERTY_DEFAULT_SFTP_DATASINK_ID,
            PROPERTY_DEFAULT_SFTP_DATASINK_NAME, PROPERTY_DEFAULT_SFTP_DATASINK_KEY);
   }

   @Provides
   @Inject
   @DefaultFtpsDatasink
   public Optional<FtpsDatasink> provideDefaultFtpsDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(FtpsDatasink.class, PROPERTY_DEFAULT_FTPS_DATASINK_ID,
            PROPERTY_DEFAULT_FTPS_DATASINK_NAME, PROPERTY_DEFAULT_FTPS_DATASINK_KEY);
   }

}
