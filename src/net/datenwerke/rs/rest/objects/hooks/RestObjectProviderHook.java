package net.datenwerke.rs.rest.objects.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.rest.objects.RestAbstractNode;

public interface RestObjectProviderHook extends Hook {

   boolean consumes(Object object);

   RestAbstractNode createRestObject(Object object);
   
   RestAbstractNode createDetailedRestObject(Object object);

}
