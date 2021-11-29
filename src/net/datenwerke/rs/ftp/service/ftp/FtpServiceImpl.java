package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
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
   public void exportIntoFtp(Object report, FtpDatasink ftpDatasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftp is disabled");
      
      try  {
         ftpSenderServiceProvider.get().sendToFtpServer(StorageType.FTP, report, ftpDatasink, config);
      } catch(IOException e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public void testFtpDatasink(FtpDatasink ftpDatasink) throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("ftp is disabled");

      exportIntoFtp("ReportServer FTP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            ftpDatasink, new DatasinkFilenameFolderConfig() {

               @Override
               public String getFilename() {
                  return "reportserver-ftp-test.txt";
               }

               @Override
               public String getFolder() {
                  return ftpDatasink.getFolder();
               }

            });
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
