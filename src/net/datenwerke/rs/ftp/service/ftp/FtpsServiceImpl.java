package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.annotations.DefaultFtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class FtpsServiceImpl implements FtpsService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<Optional<FtpsDatasink>> defaultFtpsDatasinkProvider;
   private final Provider<FtpSenderService> ftpSenderServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
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
   public void testFtpsDatasink(FtpsDatasink ftpsDatasink) throws DatasinkExportException {
       if (!datasinkServiceProvider.get().isEnabled(this))
           throw new IllegalStateException("ftps is disabled");

       exportIntoFtps("ReportServer FTPS Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
               ftpsDatasink, "reportserver-ftps-test.txt", ftpsDatasink.getFolder());

   }

   @Override
   public void exportIntoFtps(Object report, FtpsDatasink ftpsDatasink, String filename, String folder)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftps is disabled");
      
      try {
         ftpSenderServiceProvider.get().sendToFtpServer(StorageType.FTPS, report, ftpsDatasink, filename, folder);
      } catch (IOException e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public Optional<FtpsDatasink> getDefaultFtpsDatasink() {
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
