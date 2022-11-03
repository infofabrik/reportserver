package net.datenwerke.rs.rest.objects.usermanager.hookers;

import net.datenwerke.rs.rest.objects.RestAbstractNode;
import net.datenwerke.rs.rest.objects.hooks.RestObjectProviderHook;
import net.datenwerke.rs.rest.objects.usermanager.RestGroup;
import net.datenwerke.security.service.usermanager.entities.Group;

public class GroupObjectProviderHooker implements RestObjectProviderHook {

   @Override
   public boolean consumes(Object object) {
      return object instanceof Group;
   }

   @Override
   public RestAbstractNode createRestObject(Object object) {
      return RestGroup.fromGroup((Group)object);
   }

   @Override
   public RestAbstractNode createDetailedRestObject(Object object) {
      return createRestObject(object);
   }

}
