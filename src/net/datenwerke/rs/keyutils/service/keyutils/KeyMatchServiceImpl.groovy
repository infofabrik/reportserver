package net.datenwerke.rs.keyutils.service.keyutils

import org.apache.commons.configuration2.Configuration

import com.google.inject.Inject
import com.google.inject.Provider

import net.datenwerke.rs.configservice.service.configservice.ConfigService

class KeyMatchServiceImpl implements KeyMatchService {
   
   private final Provider<ConfigService> configServiceProvider
   
   @Inject
   public KeyMatchServiceImpl(
      Provider<ConfigService> configServiceProvider
      ) {
         this.configServiceProvider = configServiceProvider
      }
      
   @Override
   public Object matchToLocalEntity(String remoteKey, Closure getEntityByKey, 
      Map<String, String> keyMappings, List<MappingPrio> mappingPrios) {
      def localEntity = null
      mappingPrios.find {
         switch (it) {
            case MappingPrio.MAPPING:
               if (keyMappings.containsKey(remoteKey))
                  localEntity = getEntityByKey(keyMappings[remoteKey])
               break
            case MappingPrio.SAME_KEY:
               localEntity = getEntityByKey(remoteKey)
               break
         }
         if (localEntity)
            return true // break find loop
      }
      
      return localEntity
   }
   
   @Override
   public Object matchToLocalEntity(String remoteKey, Closure getEntityByKey,
      String configFile, String mappingsConfigPath, String priosConfigPath) {
      Configuration config = configServiceProvider.get().getConfig(configFile)
      
      List<MappingPrio> prios = getPriosFromConfig(config, priosConfigPath)
      Map<String, String> mappings = getKeyMappingsFromConfig(config, mappingsConfigPath)
      
      return matchToLocalEntity(remoteKey, getEntityByKey, mappings, prios)
   }
   
   @Override
   public List<MappingPrio> getPriosFromConfig(Configuration config, String configPath) {
      return configServiceProvider.get().parseConfigList(config, configPath)
         .collect { MappingPrio.values().find { val -> val.prio == it } }
         ?: [
            MappingPrio.MAPPING,
            MappingPrio.SAME_KEY
         ]
   }
   
   @Override
   public Map<String, String> getKeyMappingsFromConfig(Configuration config, String configPath) {
      return config.configurationsAt(configPath).collectEntries{
         [
            it.getString('remote'),
            it.getString('local')
         ]
      }
   }

   @Override
   public List<MappingPrio> getPriosFromConfig(String configFile, String configPath) {
      return getPriosFromConfig(configServiceProvider.get().getConfig(configFile), configPath)
   }

   @Override
   public Map<String, String> getKeyMappingsFromConfig(String configFile, String configPath) {
      return getKeyMappingsFromConfig(configServiceProvider.get().getConfig(configFile), configPath)
   }
}
