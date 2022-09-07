package net.datenwerke.rs.rest.objects.usermanager;

import net.datenwerke.rs.rest.objects.RestAbstractNode
import net.datenwerke.security.service.usermanager.entities.User;

public class RestUser extends RestAbstractNode {

   String title
   String firstname
   String lastname
   String sex
   String email
   String username

   public static RestUser fromUser(User user) {
      RestUser restUser = new RestUser()

      restUser.with { 
         id = user.id
         clazz = User
         title = user.title
         firstname = user.firstname
         lastname = user.lastname
         sex = user.sex.toString()
         email = user.email
         username = user.name
      }

      return restUser
   }

}
