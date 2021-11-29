package net.datenwerke.rs.ftp.service.ftp;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;

public interface FtpService extends BasicDatasinkService {

   /**
    * Sends a report to a FTP server defined in a given {@link FtpDatasink}
    * datasink. The folder defined in the {@link FtpDatasink} is overridden by the
    * <tt>folder</tt> parameter.
    * 
    * @param report      the report to send. May be a String or a byte array
    * @param ftpDatasink defines the FTP server
    * @param filename    filename to use for the report
    * @param folder      where to save the report in the FTP server. Overrides the
    *                    folder defined in the {@link FtpDatasink}
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoFtp(Object report, FtpDatasink ftpDatasink, String filename, String folder)
         throws DatasinkExportException;

   /**
    * Issues a FTP test request by creating a simple text file and sending it to
    * the specified directory in the FTP server of the datasink.
    * 
    * @param ftpDatasink the {@link FtpDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testFtpDatasink(FtpDatasink ftpDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<FtpDatasink> getDefaultFtpDatasink();

}
