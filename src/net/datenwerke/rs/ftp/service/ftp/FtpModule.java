package net.datenwerke.rs.ftp.service.ftp;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultSftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public class FtpModule extends AbstractModule {

   private static final String PROPERTY_DEFAULT_FTP_DATASINK_ID = "ftp." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_FTP_DATASINK_NAME = "ftp." + PROPERTY_DEFAULT_DATASINK_NAME;
   private static final String PROPERTY_DEFAULT_SFTP_DATASINK_ID = "sftp." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_SFTP_DATASINK_NAME = "sftp." + PROPERTY_DEFAULT_DATASINK_NAME;
   private static final String PROPERTY_DEFAULT_FTPS_DATASINK_ID = "ftps." + PROPERTY_DEFAULT_DATASINK_ID;
   private static final String PROPERTY_DEFAULT_FTPS_DATASINK_NAME = "ftps." + PROPERTY_DEFAULT_DATASINK_NAME;
   
   @Override
   protected void configure() {
      bind(FtpService.class).to(FtpServiceImpl.class);

      requestStaticInjection(FtpDatasink.class);
      requestStaticInjection(SftpDatasink.class);
      requestStaticInjection(FtpsDatasink.class);

      bind(FtpStartup.class).asEagerSingleton();
   }
   
   @Provides
   @Inject
   @DefaultFtpDatasink
   public Optional<FtpDatasink> provideDefaultFtpDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(FtpDatasink.class, PROPERTY_DEFAULT_FTP_DATASINK_ID,
            PROPERTY_DEFAULT_FTP_DATASINK_NAME);
   }
   
   @Provides
   @Inject
   @DefaultSftpDatasink
   public Optional<SftpDatasink> provideDefaultSftpDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(SftpDatasink.class, PROPERTY_DEFAULT_SFTP_DATASINK_ID,
            PROPERTY_DEFAULT_SFTP_DATASINK_NAME);
   }
   
   @Provides
   @Inject
   @DefaultFtpsDatasink
   public Optional<FtpsDatasink> provideDefaultFtpsDatasink(DatasinkService datasinkService) {
      return datasinkService.getDefaultDatasink(FtpsDatasink.class, PROPERTY_DEFAULT_FTPS_DATASINK_ID,
            PROPERTY_DEFAULT_FTPS_DATASINK_NAME);
   }

}
