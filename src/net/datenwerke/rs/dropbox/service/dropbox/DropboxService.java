package net.datenwerke.rs.dropbox.service.dropbox;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummyDropboxServiceImpl.class)
public interface DropboxService {

   /**
    * Sends a report to Dropbox, defined in a given {@link DropboxDatasink}
    * datasink. The folder defined in the {@link DropboxDatasink} is overridden by
    * the <tt>folder</tt> parameter.
    * 
    * @param report          the report to send. May be a String or a byte array
    * @param dropboxDatasink defines the Dropbox datasink to use
    * @param filename        filename to use for the report
    * @param folder          where to save the report in the Dropbox account.
    *                        Overrides the folder defined in the
    *                        {@link DropboxDatasink}
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void exportIntoDatasink(Object report, DropboxDatasink dropboxDatasink, String filename, String folder)
         throws IOException, InterruptedException, ExecutionException;

   /**
    * Summarizes {@link #isEnabled()} and
    * {@link #isSchedulingEnabled()} in a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isEnabled()} and {@link #isSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getEnabledConfigs();

   /**
    * Returns the current configuration value of Dropbox enabling. Has to be true
    * in order for reports to be sent to Dropbox datasinks.
    * 
    * @return true if Dropbox is enabled
    */
   boolean isEnabled();

   /**
    * Returns the current configuration value of Dropbox scheduling enabling.
    * Reports can only be sent to a Dropbox datasink inside a scheduling job if
    * this is true.
    * 
    * @return true if Dropbox's scheduling is enabled
    */
   boolean isSchedulingEnabled();

   /**
    * Issues a Dropbox test request by creating a simple text file and sending it
    * to the specified directory in the Dropbox of the datasink.
    * 
    * @param dropboxDatasink the {@link DropboxDatasink} to test
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void testDatasink(DropboxDatasink dropboxDatasink)
         throws IOException, InterruptedException, ExecutionException;
   
   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<DropboxDatasink> getDefaultDatasink();

}
