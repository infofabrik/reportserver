package net.datenwerke.rs.transport.service.transport.hookers;

import java.util.Optional;

import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class TransportClosedPreconditionHooker implements ApplyPreconditionHook {

   @Override
   public String getKey() {
      return "TRANSPORT_CLOSED_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      if (!transport.isClosed())
         return new PreconditionResult(Optional.of("Transport is not closed"));
         
      return new PreconditionResult(Optional.empty());
   }

}
