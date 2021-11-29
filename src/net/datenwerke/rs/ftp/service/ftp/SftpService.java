package net.datenwerke.rs.ftp.service.ftp;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public interface SftpService extends BasicDatasinkService {

   /**
    * Sends a report to a SFTP server defined in a given {@link SftpDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link SftpDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report       the report to send. May be a String or a byte array
    * @param sftpDatasink defines the SFTP server
    * @param filename     filename to use for the report
    * @param folder       where to save the report in the SFTP server. Overrides
    *                     the folder defined in the {@link SftpDatasink}
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoSftp(Object report, SftpDatasink sftpDatasink, String filename, String folder)
         throws DatasinkExportException;

   /**
    * Issues a SFTP test request by creating a simple text file and sending it to
    * the specified directory in the SFTP server of the datasink.
    * 
    * @param sftpDatasink the {@link SftpDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testSftpDatasink(SftpDatasink sftpDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<SftpDatasink> getDefaultSftpDatasink();

}
