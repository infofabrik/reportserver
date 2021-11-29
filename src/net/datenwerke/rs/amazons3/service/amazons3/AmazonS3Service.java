package net.datenwerke.rs.amazons3.service.amazons3;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyAmazonS3ServiceImpl.class)
public interface AmazonS3Service extends BasicDatasinkService {

   /**
    * Sends a report to AmazonS3, defined in a given {@link AmazonS3Datasink}
    * datasink. The folder defined in the {@link AmazonS3Datasink} is overridden by
    * the <tt>folder</tt> parameter.
    * 
    * @param report           the report to send. May be a String or a byte array
    * @param amazonS3Datasink defines the AmazonS3 datasink to use
    * @param filename         filename to use for the report
    * @param folder           where to save the report in the AmazonS3 account.
    *                         Overrides the folder defined in the
    *                         {@link AmazonS3Datasink}
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void exportIntoDatasink(Object report, AmazonS3Datasink amazonS3Datasink, String filename, String folder)
         throws IOException, InterruptedException, ExecutionException;


   /**
    * Issues a AmazonS3 test request by creating a simple text file and sending it
    * to the specified directory in the AmazonS3 of the datasink.
    * 
    * @param amazonS3Datasink the {@link AmazonS3Datasink} to test
    * @throws IOException          if an I/O error occurs
    * @throws InterruptedException if the oauth client throws InterruptedException
    * @throws ExecutionException   if the oauth client throws ExecutionException
    */
   void testDatasink(AmazonS3Datasink amazonS3Datasink)
         throws IOException, InterruptedException, ExecutionException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<AmazonS3Datasink> getDefaultDatasink();

}
