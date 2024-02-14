package net.datenwerke.rs.keyutils.service.keyutils;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;

import groovy.lang.Closure;

public interface KeyMatchService {
   
   public static final String MAPPINGS_CONFIG_FILE = "main/mappings.cf";
   public static final String DATASOURCE_PRIOS_PATH = "datasources.priorities.priority";
   public static final String DATASOURCE_MAPPINGS_PATH = "datasources.key-mappings.key-mapping";

   Object matchToLocalEntity(String remoteKey, Closure getEntityByKey, 
         Map<String, String> keyMappings, List<MappingPrio> mappingPrios);
   
   Object matchToLocalEntity(String remoteKey, Closure getEntityByKey,
         String configFile, String mappingsConfigPath, String priosConfigPath);
   
   List<MappingPrio> getPriosFromConfig(Configuration config, String configPath);
   
   List<MappingPrio> getPriosFromConfig(String configFile, String configPath);
   
   Map<String, String> getKeyMappingsFromConfig(Configuration config, String configPath);
   
   Map<String, String> getKeyMappingsFromConfig(String configFile, String configPath);
}
