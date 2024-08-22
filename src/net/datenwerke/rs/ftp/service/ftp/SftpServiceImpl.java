package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultSftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public class SftpServiceImpl implements SftpService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<Optional<SftpDatasink>> defaultSftpDatasinkProvider;
   private final Provider<FtpSenderService> ftpSenderService;

   @Inject
   public SftpServiceImpl(
         @DefaultSftpDatasink Provider<Optional<SftpDatasink>> defaultSftpDatasinkProvider,
         Provider<DatasinkService> datasinkServiceProvider, 
         Provider<FtpSenderService> ftpSenderService
         ) {
      this.defaultSftpDatasinkProvider = defaultSftpDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.ftpSenderService = ftpSenderService;
   }

   @Override
   public void doExportIntoDatasink(Object data, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("sftp is disabled");
      if (!(datasink instanceof SftpDatasink))
         throw new IllegalStateException("Not an SFTP datasink");

      SftpDatasink sftpDatasink = (SftpDatasink) datasink;

      try {
         ftpSenderService.get().sendToFtpServer(StorageType.SFTP, data, sftpDatasink, config);
      } catch (IOException e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public String getDatasinkPropertyName() {
      return "sftp";
   }

   @Override
   public StorageType getStorageType() {
      return StorageType.SFTP;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return StorageType.SFTP_SCHEDULING;
   }

   @Override
   public Optional<SftpDatasink> getDefaultDatasink() {
      return defaultSftpDatasinkProvider.get();
   }

}
