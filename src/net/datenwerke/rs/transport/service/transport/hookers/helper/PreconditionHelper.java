package net.datenwerke.rs.transport.service.transport.hookers.helper;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;

public class PreconditionHelper {

   private final Provider<ConfigService> configServiceProvider;
   
   @Inject
   public PreconditionHelper(Provider<ConfigService> configServiceProvider) {
      this.configServiceProvider = configServiceProvider;
   }
   
   public PreconditionResult checkExistingConfigFile(String configFile) {
      try {
         configServiceProvider.get().getConfig(configFile);
      } catch (Exception e) {
         return new PreconditionResult(Optional.of(configFile + " not found"));
      }
      return new PreconditionResult(Optional.empty());
   }
   
   public boolean configFileExists(String configFile) {
      try {
         configServiceProvider.get().getConfig(configFile);
      } catch (Exception e) {
         return false;
      }
      return true;
   }
}
