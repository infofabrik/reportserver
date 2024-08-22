package net.datenwerke.rs.core.service.datasinkmanager.configs;

import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;

public interface DatasinkFolderConfig extends DatasinkConfiguration {

   /**
    * Where to save the file in the datasink. Overrides the folder defined in the
    * {@link DatasinkDefinition}
    * 
    * @return where to save the file in the datasink
    */
   String getFolder();

}
