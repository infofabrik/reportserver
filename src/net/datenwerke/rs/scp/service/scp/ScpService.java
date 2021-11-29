package net.datenwerke.rs.scp.service.scp;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

@ImplementedBy(DummyScpServiceImpl.class)
public interface ScpService extends BasicDatasinkService {

   /**
    * Sends a report to a Scp server defined in a given {@link ScpDatasink}
    * <tt>datasink</tt>. The folder defined in the {@link ScpDatasink} is
    * overridden by the <tt>folder</tt> parameter.
    * 
    * @param report      the report to send. May be a String or a byte array
    * @param scpDatasink defines the datasink to use
    * @param config      configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, ScpDatasink scpDatasink, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Issues a Scp test request by creating a simple text file and sending it to
    * the specified directory in the Scp server of the datasink.
    * 
    * @param scpDatasink the {@link ScpDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testScpDatasink(ScpDatasink scpDatasink) throws DatasinkExportException;

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<ScpDatasink> getDefaultDatasink();

}
