package net.datenwerke.rs.onedrive.service.onedrive;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

@ImplementedBy(DummyOneDriveServiceImpl.class)
public interface OneDriveService extends BasicDatasinkService {
   /**
    * Sends a report to OneDrive, defined in a given {@link OneDriveDatasink}
    * datasink. The folder defined in the {@link OneDriveDatasink} is overridden by
    * the <tt>folder</tt> parameter.
    * 
    * @param report           the report to send. May be a String or a byte array
    * @param oneDriveDatasink defines the OneDrive datasink to use
    * @param filename         filename to use for the report
    * @param folder           where to save the report in the OneDrive account.
    *                         Overrides the folder defined in the
    *                         {@link OneDriveDatasink}
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws DatasinkExportException;

   /**
    * Issues a OneDrive test request by creating a simple text file and sending it
    * to the specified directory in the OneDrive of the datasink.
    * 
    * @param oneDriveDatasink the {@link OneDriveDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testDatasink(OneDriveDatasink oneDriveDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<OneDriveDatasink> getDefaultDatasink();
}