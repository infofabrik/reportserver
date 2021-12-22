package net.datenwerke.rs.configservice.service.configservice;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.configuration2.Configuration;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public interface ConfigService {

   Configuration getConfig(String identifier);

   Configuration getConfig(String identifier, boolean useCache);

   Configuration newConfig();

   void storeConfig(Configuration config, String identifier);

   void clearCache();

   void clearCache(String identifier);

   Configuration getConfigFailsafe(String identifier);

   String getConfigAsXml(String identifier);

   String getConfigAsXmlFailsafe(String identifier);
   
   /**
    * Extracts a copy of the basic configuration files to a given sub-directory of root. If the specified folder
    * is not already present it will be created
    * @param folderName name of the folder in which files should be copied
    * @return the folder where the configuration files were copied to
    */
   FileServerFolder extractBasicConfigFilesTo(String folderName) throws FileNotFoundException, IOException;
}
