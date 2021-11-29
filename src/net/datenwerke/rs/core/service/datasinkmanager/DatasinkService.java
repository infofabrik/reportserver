package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

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

}
