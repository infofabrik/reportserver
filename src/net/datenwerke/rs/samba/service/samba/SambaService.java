package net.datenwerke.rs.samba.service.samba;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@ImplementedBy(DummySambaServiceImpl.class)
public interface SambaService {

   /**
    * Sends a report to a Samba server defined in a given {@link SambaDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link SambaDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report        the report to send. May be a String or a byte array
    * @param sambaDatasink defines the datasink to use
    * @param filename      filename to use for the report
    * @param folder        extension path of the base path defined in the datasink.
    *                      Overrides the folder defined in the
    *                      {@link SambaDatasink}
    * @throws IOException     if an I/O error occurs
    */
   void exportIntoDatasink(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws IOException;

   /**
    * Summarizes {@link #isEnabled()} and {@link #isSchedulingEnabled()}
    * in a map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isEnabled()} and {@link #isSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getEnabledConfigs();

   /**
    * Returns the current configuration value of Samba enabling. Has to be true in
    * order for reports to be sent to Samba datasinks.
    * 
    * @return true if Samba is enabled
    */
   boolean isEnabled();

   /**
    * Returns the current configuration value of Samba scheduling enabling. Reports
    * can only be sent to a Samba datasink inside a scheduling job if this is true.
    * 
    * @return true if Samba's scheduling is enabled
    */
   boolean isSchedulingEnabled();

   /**
    * Issues a Samba test request by creating a simple text file and sending it to
    * the specified directory in the Samba server of the datasink.
    * 
    * @param sambaDatasink the {@link SambaDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testDatasink(SambaDatasink sambaDatasink) throws IOException;
   
   Optional<SambaDatasink> getDefaultDatasink();
}
