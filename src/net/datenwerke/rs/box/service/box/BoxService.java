package net.datenwerke.rs.box.service.box;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummyBoxServiceImpl.class)
public interface BoxService {

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
   void exportIntoBox(Object report, BoxDatasink boxDatasink, String filename, String folder) throws Exception;

   /**
    * Summarizes {@link #isBoxEnabled()} and {@link #isBoxSchedulingEnabled()} in a
    * map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isBoxEnabled()} and {@link #isBoxSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getStorageEnabledConfigs();

   /**
    * Returns the current configuration value of Box enabling. Has to be true in
    * order for reports to be sent to Box datasinks.
    * 
    * @return true if Box is enabled
    */
   boolean isBoxEnabled();

   /**
    * Returns the current configuration value of Box scheduling enabling. Reports
    * can only be sent to a Box datasink inside a scheduling job if this is true.
    * 
    * @return true if Box's scheduling is enabled
    */
   boolean isBoxSchedulingEnabled();

   /**
    * Issues a Box test request by creating a simple text file and sending it to
    * the specified directory in the Box of the datasink.
    * 
    * @param boxDatasink the {@link BoxDatasink} to test
    */
   void testBoxDatasink(BoxDatasink boxDatasink) throws Exception;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<BoxDatasink> getDefaultDatasink();

}
