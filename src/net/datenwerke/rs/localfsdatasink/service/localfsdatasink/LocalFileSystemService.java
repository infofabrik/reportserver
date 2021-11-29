package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummyLocalFileSystemServiceImpl.class)
public interface LocalFileSystemService extends BasicDatasinkService {

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