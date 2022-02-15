package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public class FtpServiceImpl implements FtpService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<Optional<FtpDatasink>> defaultFtpDatasinkProvider;
   private final Provider<FtpSenderService> ftpSenderServiceProvider;

   @Inject
   public FtpServiceImpl(
         @DefaultFtpDatasink Provider<Optional<FtpDatasink>> defaultFtpDatasinkProvider,
         Provider<DatasinkService> datasinkServiceProvider, 
         Provider<FtpSenderService> ftpSenderServiceProvider
         ) {
      this.defaultFtpDatasinkProvider = defaultFtpDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.ftpSenderServiceProvider = ftpSenderServiceProvider;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftp is disabled");
      if (!(datasink instanceof FtpDatasink))
         throw new IllegalStateException("Not an FTP datasink");

      FtpDatasink ftpDatasink = (FtpDatasink) datasink;

      try {
         ftpSenderServiceProvider.get().sendToFtpServer(StorageType.FTP, report, ftpDatasink, config);
      } catch (IOException e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public Optional<FtpDatasink> getDefaultDatasink() {
      return defaultFtpDatasinkProvider.get();
   }

   @Override
   public String getDatasinkPropertyName() {
      return "ftp";
   }

   @Override
   public StorageType getStorageType() {
      return StorageType.FTP;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return StorageType.FTP_SCHEDULING;
   }

}
