package net.datenwerke.rs.transport.service.transport.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;

public interface ApplyPreconditionHook extends Hook {

   String getKey();
   
   PreconditionResult analyze(Transport transport);
}
