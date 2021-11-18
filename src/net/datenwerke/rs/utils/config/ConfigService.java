package net.datenwerke.rs.utils.config;

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

   String getConfigAsJson(String identifier);

   String getConfigAsJsonFailsafe(String identifier);
   
   /**
    * Extracts a copy of the basic configuration files to a given directory.
    * @param path path where the configuration files should be copied to
    * @return the folder where the configuration files were copied to
    */
   FileServerFolder extractBasicConfigFilesTo(String path) throws FileNotFoundException, IOException;
}
