package net.datenwerke.rs.onedrive.service.onedrive;

import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface OneDriveService {
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
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void exportIntoOneDrive(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws IOException, InterruptedException, ExecutionException;

   /**
    * Summarizes {@link #isOneDriveEnabled()} and
    * {@link #isOneDriveSchedulingEnabled()} in a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isOneDriveEnabled()} and
    *         {@link #isOneDriveSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getStorageEnabledConfigs();

   /**
    * Returns the current configuration value of OneDrive enabling. Has to be true
    * in order for reports to be sent to OneDrive datasinks.
    * 
    * @return true if OneDrive is enabled
    */
   boolean isOneDriveEnabled();

   /**
    * Returns the current configuration value of OneDrive scheduling enabling.
    * Reports can only be sent to a OneDrive datasink inside a scheduling job if
    * this is true.
    * 
    * @return true if OneDrive's scheduling is enabled
    */
   boolean isOneDriveSchedulingEnabled();

   /**
    * Issues a OneDrive test request by creating a simple text file and sending it
    * to the specified directory in the OneDrive of the datasink.
    * 
    * @param oneDriveDatasink the {@link OneDriveDatasink} to test
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void testOneDriveDatasink(OneDriveDatasink oneDriveDatasink)
         throws IOException, InterruptedException, ExecutionException;

   Optional<OneDriveDatasink> getDefaultDatasink();
}