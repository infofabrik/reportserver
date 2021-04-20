package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;
import java.util.Map;

import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface FtpService {

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
    * @throws IOException if an I/O error occurs
    */
   void sendToFtpServer(Object report, FtpDatasink ftpDatasink, String filename, String folder) throws IOException;

   /**
    * Returns the current configuration value of FTP enabling. Has to be true in
    * order for reports to be sent to FTP datasinks.
    * 
    * @return true if FTP is enabled
    */
   boolean isFtpEnabled();

   /**
    * Summarizes {@link #isFtpEnabled()} and {@link #isFtpSchedulingEnabled()} in a
    * map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isFtpEnabled()} and {@link #isFtpSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getFtpEnabledConfigs();

   /**
    * Returns the current configuration value of FTP scheduling enabling. Reports
    * can only be sent to a FTP datasink inside a scheduling job if this is true.
    * 
    * @return true if FTP's scheduling is enabled
    */
   boolean isFtpSchedulingEnabled();

   /**
    * Issues a FTP test request by creating a simple text file and sending it to
    * the specified directory in the FTP server of the datasink.
    * 
    * @param ftpDatasink the {@link FtpDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testFtpDataSink(FtpDatasink ftpDatasink) throws IOException;

   /**
    * Issues a SFTP test request by creating a simple text file and sending it to
    * the specified directory in the SFTP server of the datasink.
    * 
    * @param sftpDatasink the {@link SftpDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testSftpDataSink(SftpDatasink sftpDatasink) throws IOException;

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
    * @throws IOException if an I/O error occurs
    */
   void sendToSftpServer(Object report, SftpDatasink sftpDatasink, String filename, String folder) throws IOException;

   /**
    * Returns the current configuration value of SFTP enabling. Has to be true in
    * order for reports to be sent to SFTP datasinks.
    * 
    * @return true if SFTP is enabled
    */
   boolean isSftpEnabled();

   /**
    * Summarizes {@link #isSftpEnabled()} and {@link #isSftpSchedulingEnabled()} in
    * a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isSftpEnabled()} and {@link #isSftpSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getSftpEnabledConfigs();

   /**
    * Returns the current configuration value of SFTP scheduling enabling. Reports
    * can only be sent to a SFTP datasink inside a scheduling job if this is true.
    * 
    * @return true if SFTP's scheduling is enabled
    */
   boolean isSftpSchedulingEnabled();

   /**
    * Issues a FTPS test request by creating a simple text file and sending it to
    * the specified directory in the FTPS server of the datasink.
    * 
    * @param sftpDatasink the {@link FtpsDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testFtpsDataSink(FtpsDatasink ftpsDatasink) throws IOException;

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
   void sendToFtpsServer(Object report, FtpsDatasink ftpsDatasink, String filename, String folder) throws IOException;

   /**
    * Returns the current configuration value of FTPS enabling. Has to be true in
    * order for reports to be sent to FTPS datasinks.
    * 
    * @return true if FTPS is enabled
    */
   boolean isFtpsEnabled();

   /**
    * Summarizes {@link #isFtpsEnabled()} and {@link #isFtpsSchedulingEnabled()} in
    * a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isFtpsEnabled()} and {@link #isFtpsSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getFtpsEnabledConfigs();

   /**
    * Returns the current configuration value of FTPS scheduling enabling. Reports
    * can only be sent to a FTPS datasink inside a scheduling job if this is true.
    * 
    * @return true if FTPS's scheduling is enabled
    */
   boolean isFtpsSchedulingEnabled();
}
