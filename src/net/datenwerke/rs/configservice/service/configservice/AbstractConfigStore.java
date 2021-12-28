package net.datenwerke.rs.configservice.service.configservice;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.XMLBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public abstract class AbstractConfigStore {

   protected BasicConfigurationBuilder<XMLConfiguration> createBaseConfig() throws ConfigurationException {

      Parameters params = new Parameters();
      XMLBuilderParameters xmlParams = params.xml().setEncoding("UTF-8");
      BasicConfigurationBuilder<XMLConfiguration> builder = new BasicConfigurationBuilder<XMLConfiguration>(
            XMLConfiguration.class).configure(xmlParams);

      return builder;
   }

   public abstract HierarchicalConfiguration loadConfig(String identifier) throws ConfigurationException;

}
