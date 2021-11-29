package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class FtpServiceImpl implements FtpService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   
   private final Provider<Optional<FtpDatasink>> defaultFtpDatasinkProvider;
   
   private final Provider<FtpSenderService> ftpSenderServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
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
   public void exportIntoFtp(Object report, FtpDatasink ftpDatasink, String filename, String folder)
         throws IOException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftp is disabled");
      
      ftpSenderServiceProvider.get().sendToFtpServer(StorageType.FTP, report, ftpDatasink, filename, folder);
   }

   @Override
   public void testFtpDatasink(FtpDatasink ftpDatasink) throws IOException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftp is disabled");

      exportIntoFtp("ReportServer FTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            ftpDatasink, "reportserver-ftp-test.txt", ftpDatasink.getFolder());
   }

   @Override
   public Optional<FtpDatasink> getDefaultFtpDatasink() {
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
