package net.datenwerke.rs.rest.objects.usermanager.hookers;

import net.datenwerke.rs.rest.objects.RestAbstractNode;
import net.datenwerke.rs.rest.objects.hooks.RestObjectProviderHook;
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnit;
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnitInfo;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;

public class OrganisationalUnitObjectProviderHooker implements RestObjectProviderHook {

   @Override
   public boolean consumes(Object object) {
      return object instanceof OrganisationalUnit;
   }

   @Override
   public RestAbstractNode createRestObject(Object object) {
      return RestOrganisationalUnitInfo.fromOrganisationalUnit((OrganisationalUnit) object);
   }

   @Override
   public RestAbstractNode createDetailedRestObject(Object object) {
      return RestOrganisationalUnit.fromOrganisationalUnit((OrganisationalUnit) object);
   }

}
