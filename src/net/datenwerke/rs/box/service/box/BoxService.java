package net.datenwerke.rs.box.service.box;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyBoxServiceImpl.class)
public interface BoxService extends BasicDatasinkService {

   /**
    * Sends a report to Box, defined in a given {@link BoxDatasink} datasink. The
    * folder defined in the {@link BoxDatasink} is overridden by the
    * <tt>folder</tt> parameter.
    * 
    * @param report      the report to send. May be a String or a byte array
    * @param boxDatasink defines the Box datasink to use
    * @param filename    filename to use for the report
    * @param folder      where to save the report in the Box account. Overrides the
    *                    folder defined in the {@link BoxDatasink}
    */
   void exportIntoDatasink(Object report, BoxDatasink boxDatasink, String filename, String folder) throws Exception;

   /**
    * Issues a Box test request by creating a simple text file and sending it to
    * the specified directory in the Box of the datasink.
    * 
    * @param boxDatasink the {@link BoxDatasink} to test
    */
   void testDatasink(BoxDatasink boxDatasink) throws Exception;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<BoxDatasink> getDefaultDatasink();

}
