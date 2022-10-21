package net.datenwerke.rs.rest.resources.usermanager

import javax.inject.Inject
import javax.inject.Provider
import javax.ws.rs.Path

import net.datenwerke.rs.rest.resources.RsRestResource
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication
import net.datenwerke.security.service.usermanager.UserManagerService

@Path("/v1.0/usermanager")
@RestAuthentication
class UserManagerResourceV10 extends RsRestResource{

   protected final Provider<UserManagerService> userManagerServiceProvider
   
   @Inject
   public UserManagerResourceV10(Provider<UserManagerService> userManagerServiceProvider
         ) {
      this.userManagerServiceProvider = userManagerServiceProvider
   }
}
