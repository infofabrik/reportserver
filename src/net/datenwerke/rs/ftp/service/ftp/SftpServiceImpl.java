package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultSftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class SftpServiceImpl implements SftpService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<Optional<SftpDatasink>> defaultSftpDatasinkProvider;
   private final Provider<FtpSenderService> ftpSenderService;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
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
   public void exportIntoSftp(Object report, SftpDatasink sftpDatasink, String filename, String folder)
         throws IOException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("sftp is disabled");
      
      ftpSenderService.get().sendToFtpServer(StorageType.SFTP, report, sftpDatasink, filename, folder);
   }

   @Override
   public void testSftpDatasink(SftpDatasink sftpDatasink) throws IOException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("sftp is disabled");

      exportIntoSftp("ReportServer SFTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            sftpDatasink, "reportserver-sftp-test.txt", sftpDatasink.getFolder());
   }
   
   @Override
   public Optional<SftpDatasink> getDefaultSftpDatasink() {
      return defaultSftpDatasinkProvider.get();
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

}