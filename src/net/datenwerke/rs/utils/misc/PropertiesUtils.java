package net.datenwerke.rs.utils.misc;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class PropertiesUtils {

    /**
     * Load a properties file from the classpath
     */
    public Configuration load(String name) throws ConfigurationException {
        return new PropertiesConfiguration(name);
    }

    /**
     * Load a Properties File
     */
    public Configuration load(File file) throws ConfigurationException {
    	return new PropertiesConfiguration(file);
    }

}
