package net.datenwerke.rs.rest.objects.usermanager.hookers;

import net.datenwerke.rs.rest.objects.RestAbstractNode;
import net.datenwerke.rs.rest.objects.hooks.RestObjectProviderHook;
import net.datenwerke.rs.rest.objects.usermanager.RestUser;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserObjectProviderHooker implements RestObjectProviderHook {

   @Override
   public boolean consumes(Object object) {
      return object instanceof User;
   }

   @Override
   public RestAbstractNode createRestObject(Object object) {
      return RestUser.fromUser((User)object);
   }

   @Override
   public RestAbstractNode createDetailedRestObject(Object object) {
      return createRestObject(object);
   }

}
