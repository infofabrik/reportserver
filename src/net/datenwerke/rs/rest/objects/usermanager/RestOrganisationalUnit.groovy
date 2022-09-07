package net.datenwerke.rs.rest.objects.usermanager

import net.datenwerke.rs.rest.objects.RestAbstractNode
import net.datenwerke.security.service.usermanager.entities.Group
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit

class RestOrganisationalUnit extends RestAbstractNode {
   
   String description
   String name
   
   public static RestOrganisationalUnit fromOrganisationalUnit(OrganisationalUnit ou) {
      RestOrganisationalUnit restOu = new RestOrganisationalUnit()

      restOu.with {
         id = ou.id
         clazz = OrganisationalUnit
         description = ou.description
         name = ou.name
      }

      return restOu
   }
}