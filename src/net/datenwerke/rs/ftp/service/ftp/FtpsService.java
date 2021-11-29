package net.datenwerke.rs.ftp.service.ftp;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;

public interface FtpsService extends BasicDatasinkService {

   /**
    * Sends a report to a FTPS server defined in a given {@link FtpsDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link FtpsDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report       the report to send. May be a String or a byte array
    * @param ftpsDatasink defines the FTPS server
    * @param config       configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoFtps(Object report, FtpsDatasink ftpsDatasink, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Issues a FTPS test request by creating a simple text file and sending it to
    * the specified directory in the FTPS server of the datasink.
    * 
    * @param ftpsDatasink the {@link FtpsDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testFtpsDatasink(FtpsDatasink ftpsDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<FtpsDatasink> getDefaultFtpsDatasink();

}
