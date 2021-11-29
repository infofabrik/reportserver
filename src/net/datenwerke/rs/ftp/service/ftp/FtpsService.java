package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;

public interface FtpsService extends BasicDatasinkService {

   /**
    * Issues a FTPS test request by creating a simple text file and sending it to
    * the specified directory in the FTPS server of the datasink.
    * 
    * @param sftpDatasink the {@link FtpsDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testFtpsDatasink(FtpsDatasink ftpsDatasink) throws IOException;

   /**
    * Sends a report to a FTPS server defined in a given {@link FtpsDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link FtpsDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report       the report to send. May be a String or a byte array
    * @param sftpDatasink defines the FTPS server
    * @param filename     filename to use for the report
    * @param folder       where to save the report in the FTPS server. Overrides
    *                     the folder defined in the {@link FtpsDatasink}
    * @throws IOException if an I/O error occurs
    */
   void exportIntoFtps(Object report, FtpsDatasink ftpsDatasink, String filename, String folder) throws IOException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<FtpsDatasink> getDefaultFtpsDatasink();

}
