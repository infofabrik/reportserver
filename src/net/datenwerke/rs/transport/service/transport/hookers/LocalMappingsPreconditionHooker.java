package net.datenwerke.rs.transport.service.transport.hookers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.keyutils.service.keyutils.KeyMatchService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hookers.helper.PreconditionHelper;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class LocalMappingsPreconditionHooker implements ApplyPreconditionHook {

   private final PreconditionHelper preconditionHelper;
   private final DatasourceService datasourceService;
   private final KeyMatchService keyMatchService;
   
   @Inject
   public LocalMappingsPreconditionHooker(
         PreconditionHelper preconditionHelper,
         DatasourceService datasourceService,
         KeyMatchService keyMatchService
         ) {
      this.preconditionHelper = preconditionHelper;
      this.datasourceService = datasourceService;
      this.keyMatchService = keyMatchService;
   }
   
   @Override
   public String getKey() {
      return "LOCAL_MAPPINGS_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      if (!preconditionHelper.configFileExists(KeyMatchService.MAPPINGS_CONFIG_FILE))
         return new PreconditionResult(Optional.of(KeyMatchService.MAPPINGS_CONFIG_FILE + " not found"));

      final Set<String> keysNotFound = new HashSet<>();
      
      keyMatchService
            .getKeyMappingsFromConfig(KeyMatchService.MAPPINGS_CONFIG_FILE, KeyMatchService.DATASOURCE_MAPPINGS_PATH)
            .values()
            .stream()
            .forEach(key -> {
               try {
                  datasourceService.getDatasourceIdFromKey(key);
               } catch (NoResultException e) {
                  keysNotFound.add(key);
               }
            });
      
      if (!keysNotFound.isEmpty())
         return new PreconditionResult(Optional.of("Local key(s) not matching local datasource(s): " + keysNotFound));

      return new PreconditionResult(Optional.empty());
   }

}
