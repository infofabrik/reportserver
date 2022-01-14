package net.datenwerke.gxtdto.client.servercommunication.callback;

import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ViolatedSecurityHook extends Hook {

   void violationOccured(ViolatedSecurityExceptionDto caught, List<ViolatedSecurityHook> chain);

}
