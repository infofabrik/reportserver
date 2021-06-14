package net.datenwerke.rs.scp.service.scp;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.google.inject.ImplementedBy;
import com.jcraft.jsch.JSchException;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

@ImplementedBy(DummyScpServiceImpl.class)
public interface ScpService {

   /**
    * Sends a report to a Scp server defined in a given {@link ScpDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link ScpDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report      the report to send. May be a String or a byte array
    * @param scpDatasink defines the datasink to use
    * @param filename    filename to use for the report
    * @param folder      extension path of the base path defined in the datasink.
    *                    Overrides the folder defined in the {@link ScpDatasink}
    * @throws IOException   if an I/O error occurs
    * @throws JSchException if error occurs while connecting with the SCP SSH
    *                       server
    */
   void sendToScpServer(Object report, ScpDatasink scpDatasink, String filename, String folder)
         throws IOException, JSchException;

   /**
    * Summarizes {@link #isScpEnabled()} and {@link #isScpSchedulingEnabled()} in a
    * map.
    * 
    * @return a map containing the enabling configuration for
    *         {@link #isScpEnabled()} and {@link #isScpSchedulingEnabled()}
    */
   Map<StorageType, Boolean> getScpEnabledConfigs();

   /**
    * Returns the current configuration value of Scp enabling. Has to be true in
    * order for reports to be sent to Scp datasinks.
    * 
    * @return true if Scp is enabled
    */
   boolean isScpEnabled();

   /**
    * Returns the current configuration value of Scp scheduling enabling. Reports
    * can only be sent to a Scp datasink inside a scheduling job if this is true.
    * 
    * @return true if Scp's scheduling is enabled
    */
   boolean isScpSchedulingEnabled();

   /**
    * Issues a Scp test request by creating a simple text file and sending it to
    * the specified directory in the Scp server of the datasink.
    * 
    * @param scpDatasink the {@link ScpDatasink} to test
    * @throws IOException   if an I/O error occurs
    * @throws JSchException if error occurs while connecting with the SCP SSH
    *                       server
    */
   void testScpDatasink(ScpDatasink scpDatasink) throws IOException, JSchException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<ScpDatasink> getDefaultDatasink();

}
