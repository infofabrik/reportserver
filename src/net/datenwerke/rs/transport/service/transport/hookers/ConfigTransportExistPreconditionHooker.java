package net.datenwerke.rs.transport.service.transport.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hookers.helper.PreconditionHelper;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class ConfigTransportExistPreconditionHooker implements ApplyPreconditionHook {

   private final PreconditionHelper preconditionHelper;
   
   @Inject
   public ConfigTransportExistPreconditionHooker(
         PreconditionHelper preconditionHelper
         ) {
      this.preconditionHelper = preconditionHelper;
   }
   
   @Override
   public String getKey() {
      return "CONFIG_TRANSPORT_EXIST_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      return preconditionHelper.checkExistingConfigFile(TransportService.CONFIG_FILE);
   }

}
