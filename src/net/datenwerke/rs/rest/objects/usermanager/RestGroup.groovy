package net.datenwerke.rs.rest.objects.usermanager

import net.datenwerke.rs.rest.objects.RestAbstractNode
import net.datenwerke.security.service.usermanager.entities.Group

class RestGroup extends RestAbstractNode {

   List ous
   List users
   List referencedGroups
   String description
   String name
   

   public static RestGroup fromGroup(Group group) {
      RestGroup restGroup = new RestGroup()

      restGroup.with { 
         id = group.id
         clazz = Group
         ous = group.ous.collect{ [id: it.id, name: it.name] }
         users = group.users.collect { [id: it.id, username: it.username] }
         referencedGroups = group.referencedGroups.collect{ [id: it.id, name: it.name] }
         description = group.description
         name = group.name
      }

      return restGroup
   }

}
