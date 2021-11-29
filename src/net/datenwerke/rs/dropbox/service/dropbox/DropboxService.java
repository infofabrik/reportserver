package net.datenwerke.rs.dropbox.service.dropbox;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

@ImplementedBy(DummyDropboxServiceImpl.class)
public interface DropboxService extends BasicDatasinkService {

   /**
    * Sends a report to Dropbox, defined in a given {@link DropboxDatasink}
    * datasink. The folder defined in the {@link DropboxDatasink} is overridden by
    * the <tt>folder</tt> parameter.
    * 
    * @param report          the report to send. May be a String or a byte array
    * @param dropboxDatasink defines the Dropbox datasink to use
    * @param config configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, DropboxDatasink dropboxDatasink, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Issues a Dropbox test request by creating a simple text file and sending it
    * to the specified directory in the Dropbox of the datasink.
    * 
    * @param dropboxDatasink the {@link DropboxDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testDatasink(DropboxDatasink dropboxDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<DropboxDatasink> getDefaultDatasink();

}
