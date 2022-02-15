package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public class FtpsServiceImpl implements FtpsService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<Optional<FtpsDatasink>> defaultFtpsDatasinkProvider;
   private final Provider<FtpSenderService> ftpSenderServiceProvider;

   @Inject
   public FtpsServiceImpl(
         @DefaultFtpsDatasink Provider<Optional<FtpsDatasink>> defaultFtpsDatasinkProvider,
         Provider<DatasinkService> datasinkServiceProvider, 
         Provider<FtpSenderService> ftpSenderServiceProvider
         ) {
      this.defaultFtpsDatasinkProvider = defaultFtpsDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.ftpSenderServiceProvider = ftpSenderServiceProvider;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftps is disabled");
      if (!(datasink instanceof FtpsDatasink))
         throw new IllegalStateException("Not an FTPS datasink");

      FtpsDatasink ftpsDatasink = (FtpsDatasink) datasink;

      try {
         ftpSenderServiceProvider.get().sendToFtpServer(StorageType.FTPS, report, ftpsDatasink, config);
      } catch (IOException e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public Optional<FtpsDatasink> getDefaultDatasink() {
      return defaultFtpsDatasinkProvider.get();
   }

   @Override
   public String getDatasinkPropertyName() {
      return "ftps";
   }

   @Override
   public StorageType getStorageType() {
      return StorageType.FTPS;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return StorageType.FTPS_SCHEDULING;
   }

}
