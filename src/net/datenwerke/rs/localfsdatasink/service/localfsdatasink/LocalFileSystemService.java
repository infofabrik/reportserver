package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummyLocalFileSystemServiceImpl.class)
public interface LocalFileSystemService {

   /**
    * Sends a report to a local directory defined in a given
    * {@link LocalFileSystemDatasink} <tt>datasink</tt>. The folder defined in the
    * {@link LocalFileSystemDatasink} is overridden by the <tt>folder</tt>
    * parameter.
    * 
    * @param report                  the report to send. May be a String or a byte
    *                                array
    * @param localFileSystemDatasink defines the datasink to use
    * @param filename                filename to use for the report
    * @param folder                  extension path of the base path defined in the
    *                                datasink. Overrides the folder defined in the
    *                                {@link LocalFileSystemDatasink}
    * @throws IOException if an I/O error occurs
    */
   void exportIntoDatasink(Object report, LocalFileSystemDatasink localFileSystemDatasink, String filename,
         String folder) throws IOException;

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
    * Returns the current configuration value of local filesystem datasink
    * enabling. Has to be true in order for reports to be sent to local filesystem
    * datasinks.
    * 
    * @return true if local filesystem is enabled
    */
   boolean isEnabled();

   /**
    * Returns the current configuration value of {@link LocalFileSystemDatasink}
    * scheduling enabling. Reports can only be sent to a
    * {@link LocalFileSystemDatasink} inside a scheduling job if this is true.
    * 
    * @return true if local filesystem's scheduling is enabled
    */
   boolean isSchedulingEnabled();

   /**
    * Issues a {@link LocalFileSystemDatasink} test request by creating a simple
    * text file and sending it to the specified directory of the datasink.
    * 
    * @param localFileSystemDatasink the {@link LocalFileSystemDatasink} to test
    * 
    * @throws IOException if an I/O error occurs
    */
   void testDatasink(LocalFileSystemDatasink localFileSystemDatasink) throws IOException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<LocalFileSystemDatasink> getDefaultDatasink();

}