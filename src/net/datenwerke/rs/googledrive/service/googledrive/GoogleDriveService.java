package net.datenwerke.rs.googledrive.service.googledrive;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummyGoogleDriveServiceImpl.class)
public interface GoogleDriveService {
   /**
    * Sends a report to GoogleDrive, defined in a given {@link GoogleDriveDatasink}
    * datasink. The folder defined in the {@link GoogleDriveDatasink} is overridden
    * by the <tt>folder</tt> parameter.
    * 
    * @param report              the report to send. May be a String or a byte
    *                            array
    * @param googleDriveDatasink defines the GoogleDrive datasink to use
    * @param filename            filename to use for the report
    * @param folder              where to save the report in the GoogleDrive
    *                            account. Overrides the folder defined in the
    *                            {@link GoogleDriveDatasink}
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    * @throws JSONException 
    */
   void exportIntoDatasink(Object report, GoogleDriveDatasink googleDriveDatasink, String filename, String folder)
         throws Exception;

   /**
    * Summarizes {@link #isEnabled()} and
    * {@link #isSchedulingEnabled()} in a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isEnabled()} and
    *         {@link #isSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getEnabledConfigs();

   /**
    * Returns the current configuration value of GoogleDrive enabling. Has to be
    * true in order for reports to be sent to GoogleDrive datasinks.
    * 
    * @return true if GoogleDrive is enabled
    */
   boolean isEnabled();

   /**
    * Returns the current configuration value of GoogleDrive scheduling enabling.
    * Reports can only be sent to a GoogleDrive datasink inside a scheduling job if
    * this is true.
    * 
    * @return true if GoogleDrive's scheduling is enabled
    */
   boolean isSchedulingEnabled();

   /**
    * Issues a GoogleDrive test request by creating a simple text file and sending
    * it to the specified directory in the GoogleDrive of the datasink.
    * 
    * @param googleDriveDatasink the {@link GoogleDriveDatasink} to test
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    * @throws JSONException 
    */
   void testDatasink(GoogleDriveDatasink googleDriveDatasink)
         throws Exception;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<GoogleDriveDatasink> getDefaultDatasink();

}
