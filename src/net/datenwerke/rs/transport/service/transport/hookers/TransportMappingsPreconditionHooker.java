package net.datenwerke.rs.transport.service.transport.hookers;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.keyutils.service.keyutils.KeyMatchService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hookers.helper.PreconditionHelper;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class TransportMappingsPreconditionHooker implements ApplyPreconditionHook {

   private final PreconditionHelper preconditionHelper;
   private final TransportService transportService;
   private final KeyMatchService keyMatchService;
   
   @Inject
   public TransportMappingsPreconditionHooker(
         PreconditionHelper preconditionHelper,
         TransportService transportService,
         KeyMatchService keyMatchService
         ) {
      this.preconditionHelper = preconditionHelper;
      this.transportService = transportService;
      this.keyMatchService = keyMatchService;
   }
   
   @Override
   public String getKey() {
      return "TRANSPORT_MAPPINGS_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      if (!preconditionHelper.configFileExists(KeyMatchService.MAPPINGS_CONFIG_FILE))
         return new PreconditionResult(Optional.of(KeyMatchService.MAPPINGS_CONFIG_FILE + " not found"));

      List<String> remoteKeys = transportService.getRemoteReferenceKeys(transport.getXml());
      
      Set<String> definedRemoteKeys = keyMatchService.getKeyMappingsFromConfig(KeyMatchService.MAPPINGS_CONFIG_FILE,
            KeyMatchService.DATASOURCE_MAPPINGS_PATH).keySet();
      
      Set<String> missingMappings = remoteKeys
         .stream()
         .filter(remoteKey -> !definedRemoteKeys.contains(remoteKey))
         .collect(toSet());
      
      if (!missingMappings.isEmpty())
         return new PreconditionResult(Optional.of("Remote key(s) without mapping(s): " + missingMappings));
      
      return new PreconditionResult(Optional.empty());
   }

}
