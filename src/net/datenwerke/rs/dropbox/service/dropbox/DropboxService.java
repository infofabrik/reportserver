package net.datenwerke.rs.dropbox.service.dropbox;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
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
