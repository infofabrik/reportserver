package net.datenwerke.rs.utils.misc;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class PropertiesUtils {

    /**
     * Load a properties file from the classpath
     */
    public Configuration load(String name) throws ConfigurationException {
       FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
             new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
             .configure(new Parameters().properties()
                 .setFileName(name));
        return builder.getConfiguration();
    }

    /**
     * Load a Properties File
     */
    public Configuration load(File file) throws ConfigurationException {
       FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
             new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
             .configure(new Parameters().properties()
                 .setFile(file));
        return builder.getConfiguration();
    }

}
