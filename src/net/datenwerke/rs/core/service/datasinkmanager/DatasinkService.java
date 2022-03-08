package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Map;
import java.util.Optional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public interface DatasinkService {

   String getDefaultFolder(FolderedDatasink datasink);

   /**
    * Returns the current configuration value of datasink enabling. Has to be true
    * in order for reports to be sent to the specified datasink type.
    * 
    * @param datasinkService the datasink service
    * @return true if the datasink type is enabled
    */
   boolean isEnabled(BasicDatasinkService datasinkService);

   /**
    * Returns the current configuration value of scheduling enabling. Reports can
    * only be sent to the specified datasink type inside a scheduling job if this
    * is true.
    * 
    * @param datasinkService the datasink service
    * @return true if datasink's scheduling is enabled
    */
   boolean isSchedulingEnabled(BasicDatasinkService datasinkService);

   /**
    * Summarizes {@link #isEnabled(DatasinkDefinition))} and
    * {@link #isSchedulingEnabled(DatasinkDefinition))} in a map.
    * 
    * @param datasinkService the datasink service
    * @return a map containing the enabling configuration for
    *         {@link #isEnabled(DatasinkDefinition))} and
    *         {@link #isSchedulingEnabled(DatasinkDefinition))}
    */
   Map<StorageType, Boolean> getEnabledConfigs(BasicDatasinkService datasinkService);

   /**
    * Issues a datasink test request by creating a simple text file and sending it
    * to the specified directory in the specified datasink.
    * 
    * @param datasinkDefinition the {@link DatasinkDefinition} to test
    * @param config             the datasink export configuration
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testDatasink(DatasinkDefinition datasinkDefinition, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Sends a data to the specified datasink, defined in a given
    * {@link DatasinkDefinition} datasink.
    * 
    * @param report   the data to send. May be a String, a byte array or a
    *                 {@link TableDBDataSource}
    * @param user     the {@link User}
    * @param datasink the {@link DatasinkDefinition} to send
    * @param config   configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Sends a data to the specified datasink, defined in a given
    * {@link DatasinkDefinition} datasink.
    * 
    * @param report   the data to send. May be a String, a byte array or a
    *                 {@link TableDBDataSource}
    * @param datasink the {@link DatasinkDefinition} to send
    * @param config   configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException;
   
   /**
    * Sends a data to the specified datasink, defined in a given
    * {@link DatasinkDefinition} datasink. Uses datasink default configuration.
    * 
    * @param report   the data to send. May be a String, a byte array or a
    *                 {@link TableDBDataSource}
    * @param datasink the {@link DatasinkDefinition} to send
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, DatasinkDefinition datasink)
         throws DatasinkExportException;

   Optional<? extends DatasinkDefinition> getDefaultDatasink(BasicDatasinkService basicDatasinkService);

   void exportFileIntoDatasink(AbstractFileServerNodeDto fileDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder, boolean compressed) throws ServerCallFailedException;

}
