package net.datenwerke.rs.core.service.datasinkmanager.configs;

public interface DatasinkFilenameConfig extends DatasinkConfiguration {

   /**
    * The filename to use for the report
    * 
    * @return fhe filename to use for the export
    */
   String getFilename();

}
