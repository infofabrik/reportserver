package net.datenwerke.rs.rest.objects.usermanager

import net.datenwerke.rs.rest.objects.RestAbstractNode
import net.datenwerke.security.service.usermanager.entities.Group
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit

class RestOrganisationalUnitInfo extends RestAbstractNode {
   
   String description
   String name
   
   public static RestOrganisationalUnitInfo fromOrganisationalUnit(OrganisationalUnit ou) {
      RestOrganisationalUnitInfo restOu = new RestOrganisationalUnitInfo()

      restOu.with {
         id = ou.id
         clazz = OrganisationalUnit
         description = ou.description
         name = ou.name
      }

      return restOu
   }
}